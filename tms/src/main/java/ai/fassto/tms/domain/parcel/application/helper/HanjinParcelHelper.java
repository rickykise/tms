package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.domain.parcel.application.port.output.api.hanjin.HanjinParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.port.output.repository.HanjinParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Fare;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.application.vo.invoice.HanjinInvoiceData;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.externalservice.parcel.hanjin.vo.enums.DomRgnType;
import org.springframework.stereotype.Component;

@Component
public class HanjinParcelHelper extends CompanySpecificHelper {
    private final HanjinParcelApi hanjinParcelApi;
    private final HanjinParcelRepository hanjinParcelRepository;

    public HanjinParcelHelper(FasstoParcelRepository fasstoParcelRepository, SweetTrackerParcelApi sweetTrackerParcelApi, HanjinParcelApi hanjinParcelApi, HanjinParcelRepository hanjinParcelRepository) {
        super(fasstoParcelRepository, sweetTrackerParcelApi);
        this.hanjinParcelApi = hanjinParcelApi;
        this.hanjinParcelRepository = hanjinParcelRepository;
    }

    @Override
    public ParcelCompanyType parcelCompanyType() {
        return ParcelCompanyType.HANJIN;
    }

    @Override
    public void collectPreCallInvoice(Parcel parcel) {
        if (super.checkPreCallInvoice(parcel)) {
            return;
        }
        if (parcel.isHanjinGuaranteeOrder()) {
            parcel.collectPreCallInvoice(fasstoParcelRepository.collectHanjinGuaranteeInvoice(parcel.getDeliveryInfo(), parcel.getFmsInfo()));
            return;
        }
        parcel.collectPreCallInvoice(fasstoParcelRepository.collectInvoice(parcel.getDeliveryInfo(), parcel.getFmsInfo()));
    }

    @Override
    public void updateContractCode(Parcel parcel) {
        if (parcel.isHanjinGuaranteeOrder()) {
            parcel.updateContractCode(
                    fasstoParcelRepository
                            .getHanjinGuaranteeCenterContractCode(parcel.getFmsInfo(), parcel.getDeliveryInfo())
                            .contractCode()
            );
            return;
        }
        super.updateContractCode(parcel);
    }

    @Override
    protected void collectFinalCallInvoice(Parcel parcel) {
        if (parcel.isHanjinGuaranteeOrder()) {
            parcel.getBoxes()
                    .boxesNotHaveInvoice()
                    .forEach(box ->
                            box.collectInvoiceNo(
                                    fasstoParcelRepository.collectHanjinGuaranteeInvoice(parcel.getDeliveryInfo(), parcel.getFmsInfo())
                            )
                    );
            return;
        }
        super.collectFinalCallInvoice(parcel);
    }

    @Override
    protected void checkIsRegisteredInvoice(Parcel parcel) {
        hanjinParcelRepository.checkIsRegisteredInvoice(parcel);
    }


    @Override
    protected void registerParcel(Parcel parcel) {
        parcel.receiveInvoiceData(this.checkAddress(parcel));
        super.markIsIntegratedInvoice(parcel);

        hanjinParcelRepository.registerParcel(parcel);
    }

    private InvoiceData checkAddress(Parcel parcel) {
        HanjinInvoiceData invoiceData = (HanjinInvoiceData) hanjinParcelApi.checkAddress(parcel);

        int airFare = DomRgnType.JEJU.getMeans().equals(invoiceData.getDomRgn()) ? 3000 : 0; // 제주운임 (파스토 계약 단가: 3000원)
        int shipFare = DomRgnType.OTHER.getMeans().equals(invoiceData.getDomRgn()) ? 5000 : 0; // 도선운임 (파스토 계약 단가: 5000원)
        parcel.insertFare(new Fare(0, 0, airFare, shipFare, 0));

        return invoiceData;
    }

    @Override
    public void insertInvoiceData(Parcel parcel) {
        super.fasstoParcelRepository.insertHanjinInvoiceData(parcel);
    }
}

package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.domain.parcel.application.port.output.api.chainlogis.ChainLogisParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.application.vo.invoice.ChainLogisInvoiceData;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisInvoiceDataDto;
import org.springframework.stereotype.Component;

@Component
public class ChainLogisParcelHelper extends CompanySpecificHelper {
    private final ChainLogisParcelApi chainLogisParcelApi;

    public ChainLogisParcelHelper(FasstoParcelRepository fasstoParcelRepository, SweetTrackerParcelApi sweetTrackerParcelApi, ChainLogisParcelApi chainLogisParcelApi) {
        super(fasstoParcelRepository, sweetTrackerParcelApi);
        this.chainLogisParcelApi = chainLogisParcelApi;
    }

    @Override
    public ParcelCompanyType parcelCompanyType() {
        return ParcelCompanyType.CHAINLOGIS;
    }

    @Override
    public void collectPreCallInvoice(Parcel parcel) {
    }

    @Override
    public void checkFasstoDeliveryUnavailableArea(Parcel parcel) {
    }

    @Override
    protected void collectFinalCallInvoice(Parcel parcel) {
    }

    @Override
    public void updateContractCode(Parcel parcel) {
    }

    @Override
    protected void checkIsRegisteredInvoice(Parcel parcel) {
    }

    @Override
    protected void registerParcel(Parcel parcel) {
        for (Box box : parcel.getBoxes().boxes()) {
            final ChainLogisInvoiceDataDto dto =
                    chainLogisParcelApi
                            .registerParcel(parcel.getSender(), parcel.getReceiver(), parcel.getFmsInfo(), parcel.getMemo(), box);

            parcel.receiveInvoiceData(
                    ChainLogisInvoiceData.builder()
                            .dongGroup(dto.dongGroup())
                            .build()
            );

            box.collectInvoiceNo(dto.invoiceNo());
        }
    }

    @Override
    public void insertInvoiceData(Parcel parcel) {
        fasstoParcelRepository.insertChainLogisInvoiceData(parcel);
    }
}

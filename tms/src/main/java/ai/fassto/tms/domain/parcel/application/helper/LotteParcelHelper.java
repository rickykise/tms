package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.LotteBrnclctDto;
import ai.fassto.tms.domain.parcel.application.port.output.api.lotte.LotteParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.port.output.repository.LotteParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.Fare;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteParcelInfoDto;
import org.springframework.stereotype.Component;

@Component
public class LotteParcelHelper extends CompanySpecificHelper {
    private final LotteParcelRepository lotteParcelRepository;
    private final LotteParcelApi lotteParcelApi;

    public LotteParcelHelper(FasstoParcelRepository fasstoParcelRepository, SweetTrackerParcelApi sweetTrackerParcelApi, LotteParcelRepository lotteParcelRepository, LotteParcelApi lotteParcelApi) {
        super(fasstoParcelRepository, sweetTrackerParcelApi);
        this.lotteParcelRepository = lotteParcelRepository;
        this.lotteParcelApi = lotteParcelApi;
    }


    @Override
    public ParcelCompanyType parcelCompanyType() {
        return ParcelCompanyType.LOTTE;
    }

    @Override
    protected void checkIsRegisteredInvoice(Parcel parcel) {
        lotteParcelRepository.checkIsRegisteredInvoice(parcel);
    }

    @Override
    protected void registerParcel(Parcel parcel) {
        parcel.receiveInvoiceData(this.checkAddress(parcel));

        lotteParcelRepository.registerParcel(parcel);
    }

    private InvoiceData checkAddress(Parcel parcel) {
        final LotteParcelInfoDto infoDto = lotteParcelApi.checkAddress(
                parcel.getSender(), parcel.getReceiver(), parcel.getDeliveryInfo()
        );

        parcel.insertFare(new Fare(0, 0, infoDto.airFare(), infoDto.shipFare(), 0));

        return infoDto.toLotteInvoiceData(
                this.invoiceBrnclctInfo(parcel.getFmsInfo(), parcel.getDeliveryInfo())
        );
    }

    /**
     * 롯데택배 송장을 위해 필요한 정보를 파스토 DB 에서 가져오는 함수
     */
    private LotteBrnclctDto invoiceBrnclctInfo(FmsInfo fmsInfo, DeliveryInfo deliveryInfo) {
        return fasstoParcelRepository.selectBrnclctInfo(fmsInfo, deliveryInfo);
    }

    @Override
    public void insertInvoiceData(Parcel parcel) {
        fasstoParcelRepository.insertLotteInvoiceData(parcel);
    }
}

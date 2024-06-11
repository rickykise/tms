package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import org.springframework.stereotype.Component;

@Component
public class EcremmoceParcelHelper extends CompanySpecificHelper {
    public EcremmoceParcelHelper(FasstoParcelRepository fasstoParcelRepository, SweetTrackerParcelApi sweetTrackerParcelApi) {
        super(fasstoParcelRepository, sweetTrackerParcelApi);
    }

    @Override
    public ParcelCompanyType parcelCompanyType() {
        return ParcelCompanyType.ECREMMOCE;
    }

    @Override
    public void collectPreCallInvoice(Parcel parcel) {
    }

    @Override
    public void checkFasstoDeliveryUnavailableArea(Parcel parcel) {
    }

    @Override
    public void updateContractCode(Parcel parcel) {
    }

    @Override
    protected void collectFinalCallInvoice(Parcel parcel) {
        parcel.distributeEcremmoceInvoice();
    }

    @Override
    protected void checkIsRegisteredInvoice(Parcel parcel) {
    }

    /**
     * 단순히 기등록된 송장 정보만 가져온다.
     */
    @Override
    protected void registerParcel(Parcel parcel) {
        parcel.receiveInvoiceData(
                fasstoParcelRepository.selectEcremmoceParcelData(parcel.getFmsInfo())
        );
    }

    @Override
    public void insertInvoiceData(Parcel parcel) {
        fasstoParcelRepository.insertEcremmoceInvoiceData(parcel);
    }

    @Override
    public void requestTrackingParcel(Parcel parcel) {
    }
}

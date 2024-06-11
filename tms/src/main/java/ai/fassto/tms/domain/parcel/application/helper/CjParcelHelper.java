package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.domain.parcel.application.port.output.api.cj.CjParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.CjParcelRepository;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResultDto;
import org.springframework.stereotype.Component;

@Component
public class CjParcelHelper extends CompanySpecificHelper {
    private final CjParcelApi cjParcelApi;
    private final CjParcelRepository cjParcelRepository;

    public CjParcelHelper(FasstoParcelRepository fasstoParcelRepository, SweetTrackerParcelApi sweetTrackerParcelApi, CjParcelApi cjParcelApi, CjParcelRepository cjParcelRepository) {
        super(fasstoParcelRepository, sweetTrackerParcelApi);
        this.cjParcelApi = cjParcelApi;
        this.cjParcelRepository = cjParcelRepository;
    }

    @Override
    public ParcelCompanyType parcelCompanyType() {
        return ParcelCompanyType.CJ;
    }

    @Override
    protected void checkIsRegisteredInvoice(Parcel parcel) {
        cjParcelRepository.checkIsRegisteredInvoice(parcel);
    }

    @Override
    protected void registerParcel(Parcel parcel) {
        this.checkAddressAndFare(parcel);
        super.markIsIntegratedInvoice(parcel);

        cjParcelRepository.registerParcel(parcel);
    }

    private void checkAddressAndFare(Parcel parcel) {
        for (int i = 0; i < parcel.getBoxes().boxSize(); i++) {
            final Box box = parcel.getBoxes().boxes().get(i);

            final CjAddrResultDto resultDto = cjParcelApi.checkAddress(
                    parcel.getSender(), parcel.getReceiver(), box
            );

            if (i == 0) {
                parcel.senderAddressConvertToCheckedAddress(resultDto.toSenderAddress());
                parcel.receiverAddressConvertToCheckedAddress(resultDto.toReceiverAddress());
                parcel.receiveInvoiceData(resultDto.toCjInvoiceData());
            }

            box.insertBoxFare(resultDto.toFare());
        }
    }

    @Override
    public void insertInvoiceData(Parcel parcel) {
        fasstoParcelRepository.insertCjInvoiceData(parcel);
    }
}

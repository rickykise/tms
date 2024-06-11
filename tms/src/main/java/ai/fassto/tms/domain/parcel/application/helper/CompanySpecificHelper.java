package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.SenderInfoDto;
import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class CompanySpecificHelper {
    protected final FasstoParcelRepository fasstoParcelRepository;
    protected final SweetTrackerParcelApi sweetTrackerParcelApi;

    public abstract ParcelCompanyType parcelCompanyType();

    public void collectPreCallInvoice(Parcel parcel) {
        if (this.checkPreCallInvoice(parcel)) {
            return;
        }
        parcel.collectPreCallInvoice(fasstoParcelRepository.collectInvoice(parcel.getDeliveryInfo(), parcel.getFmsInfo()));
    }

    protected boolean checkPreCallInvoice(Parcel parcel) {
        return parcel.isPreCallInvoice();
    }

    public void checkFasstoDeliveryUnavailableArea(Parcel parcel) {
        fasstoParcelRepository.checkDeliveryUnavailable(parcel.getFmsInfo(), parcel.getReceiver(), parcel.getDeliveryInfo());
    }

    public void updateContractCode(Parcel parcel) {
        parcel.updateContractCode(
                fasstoParcelRepository
                        .getCenterContractCode(parcel.getFmsInfo(), parcel.getDeliveryInfo())
                        .contractCode()
        );
    }

    public void itemNameSetting(Parcel parcel) {
        for (Box box : parcel.getBoxes().boxes()) {
            box.getItems()
                    .itemsNotHaveName()
                    .forEach(item -> item.itemNameSetting(
                            fasstoParcelRepository.getItemName(item)
                                    .trimGodNm()
                    ));
        }
    }

    /**
     * [보내는사람]
     *  보내는사람 정보가(senderName) 없다면 고객사명(customerName) 표기
     *  보내는사람 정보가(senderName) 있다면 보내는사람(senderName) 표기
     * [보내는사람 연락처]
     *  보내는사람 연락처 정보가(senderPhoneNumber) 없다면 고객사의 연락처(솔로체인에서 senderPhoneNumber필드에 고객사의 연락처를 보내주고 있음) 표기
     *  보내는사람 연락처 정보가(senderPhoneNumber) 있다면 보내는사람 연락처(senderPhoneNumber) 표기
     */
    public void senderInfoSetting(Parcel parcel) {
        SenderInfoDto senderInfo = fasstoParcelRepository.getSenderInfo(parcel.getFmsInfo());
        if (Objects.isNull(senderInfo)) {
            senderInfo = SenderInfoDto.builder().build();
        }

        String senderName = senderInfo.senderName();
        String senderPhoneNumber = senderInfo.senderTelNo();

        if (StringUtils.isNotEmpty(senderPhoneNumber)) {
            parcel.getSender().getPhone().senderPhoneNumberSetting(senderPhoneNumber);
        }

        if (StringUtils.isEmpty(senderName)) {
            senderName = parcel.getFmsInfo().customerName();
        }
        parcel.getSender().senderNameSetting(senderName);
    }

    @Transactional
    public void registerParcelProcess(Parcel parcel) {
        collectFinalCallInvoice(parcel);
        checkIsRegisteredInvoice(parcel);
        registerParcel(parcel);
    }

    protected void collectFinalCallInvoice(Parcel parcel) {
        parcel.getBoxes()
                .boxesNotHaveInvoice()
                .forEach(box ->
                        box.collectInvoiceNo(
                                fasstoParcelRepository.collectInvoice(parcel.getDeliveryInfo(), parcel.getFmsInfo())
                        )
                );
    }

    protected void markIsIntegratedInvoice(Parcel parcel) {
        parcel.getInvoiceData().markIsIntegratedInvoice(
                fasstoParcelRepository.isIntegratedInvoice(parcel.getFmsInfo())
        );
    }

    protected abstract void checkIsRegisteredInvoice(Parcel parcel);

    protected abstract void registerParcel(Parcel parcel);

    public abstract void insertInvoiceData(Parcel parcel);

    public void requestTrackingParcel(Parcel parcel) {
        sweetTrackerParcelApi.requestTrackingParcel(parcel.getFmsInfo(), parcel.getDeliveryInfo(), parcel.getBoxes());
    }
}

package ai.fassto.tms.externalservice.parcel.chainlogis.dto;

import lombok.Builder;

// senderAddress, senderAddressDetail 값은 사용하지 않고
// customSenderAddress, customSenderAddressDetail 값으로 사용
// (서울 외 지역으로 설정 할 경우 체인로지스 측 에러 발생하여 협의 후, 해당 값으로 사용)
@Builder
public record ChainLogisRegisterRequestDto(
        String spotCode,
        String receiverName,
        String receiverMobile,
        String receiverAddress,
        String receiverAddressDetail,
        String productName,
        String memoFromCustomer,
        String orderIdFromCorp,
        String frontdoorPassword,
        String etc1,
        String senderName,
        String senderMobile,
        String customSenderAddress,
        String customSenderAddressDetail,
        int limitedDeliveryHours            // 배송 제한 시간(단위: 시간)
) {
    private static final String SPOT_CODE = "10185";

    public ChainLogisRegisterRequestDto {
        spotCode = SPOT_CODE;
        limitedDeliveryHours = 0;
    }
}

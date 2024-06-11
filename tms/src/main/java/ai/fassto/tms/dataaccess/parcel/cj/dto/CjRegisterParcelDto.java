package ai.fassto.tms.dataaccess.parcel.cj.dto;

import lombok.Builder;

@Builder
public record CjRegisterParcelDto(
        String contractCode,
        String customerUseNo,
        String invoiceNo,
        String receiptType,
        String receiptDate,
        String pickDate,
        String freightType,
        String boxType,
        int boxQty,
        String orderNo,
        String displayProductName,
        int productQty,
        String senderName,
        String senderNumber1,
        String senderNumber2,
        String senderNumber3,
        String senderEtcNumber1,
        String senderEtcNumber2,
        String senderEtcNumber3,
        String senderZipCode,
        String senderAddress,
        String senderAddressDetail,
        String receiverName,
        String receiverNumber1,
        String receiverNumber2,
        String receiverNumber3,
        String receiverEtcNumber1,
        String receiverEtcNumber2,
        String receiverEtcNumber3,
        String receiverZipCode,
        String receiverAddress,
        String receiverAddressDetail,
        String shipRequestMemo
) {
}

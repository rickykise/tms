package ai.fassto.tms.dataaccess.parcel.hanjin.dto;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.core.exception.ParcelRegisterFailException;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class RegisterParcelDto {
    private static String SUCCESS = "Y";

    private String customerUseNo;
    private String invoiceNo;
    private String shippingType;
    private String contractCode;
    private String collectionDate;
    private String senderZipCode;
    private String senderAddress;
    private String senderAddressDetail;
    private String senderName;
    private String senderPhone;
    private String senderCellPhone;
    private String receiverZipCode;
    private String receiverAddress;
    private String receiverAddressDetail;
    private String receiverName;
    private String receiverPhone;
    private String receiverCellPhone;
    private String shipRequestMemo;
    private String displayProductName;
    private int boxQty;
    private String paymentType;
    private String boxType;
    private String hanjinInputValue;

    private String isResult;
    private String errorCode;
    private String errorMessage;


    public void checkSuccess() {
        if (!SUCCESS.equals(this.isResult)) {
            throw new ParcelRegisterFailException(TmsResultType.PARCEL_REGISTER_ERROR);
        }
    }
}

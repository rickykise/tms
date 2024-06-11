package ai.fassto.tms.dataaccess.parcel.cj.mapper;

import ai.fassto.tms.common.util.DateUtil;
import ai.fassto.tms.dataaccess.parcel.cj.dto.CjRegisterParcelDto;
import ai.fassto.tms.dataaccess.parcel.cj.dto.CustomerUseNoDto;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CjParcelDataAccessMapper {
    private static final String CJ_FREIGHT_TYPE = "03";
    private static final String CJ_REGISTER_TYPE = "01";

    public List<CjRegisterParcelDto> toCjRegisterParcelDto(Parcel parcel) {
        return parcel.getBoxes()
                .stream()
                .map(box ->
                        CjRegisterParcelDto.builder()
                                .contractCode(parcel.getDeliveryInfo().getContractCode())
                                .customerUseNo(parcel.customerUseNoBy(box))
                                .invoiceNo(box.getInvoiceNo())
                                .receiptType(CJ_REGISTER_TYPE)
                                .receiptDate(DateUtil.getTodayYYYYMMDD())
                                .pickDate(DateUtil.parseToStringDateYYYYMMDD(parcel.getDeliveryInfo().getPickDate()))
                                .freightType(CJ_FREIGHT_TYPE)
                                .boxType(box.getCjBoxType())
                                .boxQty(1)
                                .orderNo(parcel.getFmsInfo().orderNo())
                                .displayProductName(box.displayItemName())
                                .productQty(box.totalItemQty())
                                .senderName(parcel.getSender().getName())
                                .senderNumber1(parcel.getSender().getPhone().getPhoneBy(0))
                                .senderNumber2(parcel.getSender().getPhone().getPhoneBy(1))
                                .senderNumber3(parcel.getSender().getPhone().getPhoneBy(2))
                                .senderEtcNumber1(parcel.getSender().getPhone().getPhoneEtcBy(0))
                                .senderEtcNumber2(parcel.getSender().getPhone().getPhoneEtcBy(1))
                                .senderEtcNumber3(parcel.getSender().getPhone().getPhoneEtcBy(2))
                                .senderZipCode(parcel.getSender().getAddress().getZipCode())
                                .senderAddress(parcel.getSender().getAddress().getAddress())
                                .senderAddressDetail(parcel.getSender().getAddress().getAddressDetail())
                                .receiverName(parcel.getReceiver().getName())
                                .receiverNumber1(parcel.getReceiver().getPhone().getPhoneBy(0))
                                .receiverNumber2(parcel.getReceiver().getPhone().getPhoneBy(1))
                                .receiverNumber3(parcel.getReceiver().getPhone().getPhoneBy(2))
                                .receiverEtcNumber1(parcel.getReceiver().getPhone().getPhoneEtcBy(0))
                                .receiverEtcNumber2(parcel.getReceiver().getPhone().getPhoneEtcBy(1))
                                .receiverEtcNumber3(parcel.getReceiver().getPhone().getPhoneEtcBy(2))
                                .receiverZipCode(parcel.getReceiver().getAddress().getZipCode())
                                .receiverAddress(parcel.getReceiver().getAddress().getAddress())
                                .receiverAddressDetail(parcel.getReceiver().getAddress().getAddressDetail())
                                .shipRequestMemo(parcel.getMemo().getShipRequestMemo())
                                .build())
                .collect(Collectors.toList());
    }

    public List<CustomerUseNoDto> toCustomerUseNoDto(Parcel parcel) {
        return parcel.getBoxes()
                .stream()
                .map(box -> new CustomerUseNoDto(parcel.customerUseNoBy(box)))
                .collect(Collectors.toList());
    }
}

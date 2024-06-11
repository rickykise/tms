package ai.fassto.tms.dataaccess.parcel.hanjin.mapper;

import ai.fassto.tms.common.util.DateUtil;
import ai.fassto.tms.dataaccess.parcel.hanjin.dto.CustomerUseNoDto;
import ai.fassto.tms.dataaccess.parcel.hanjin.dto.RegisterParcelDto;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HanjinParcelDataAccessMapper {
    private static final String REGISTER_SHIPPING_TYPE = "S";
    private static final String PAYMENT_TYPE = "CD";

    public List<RegisterParcelDto> toRegisterParcelDto(Parcel parcel) {
        return parcel.getBoxes()
                .stream()
                .map(box ->
                        RegisterParcelDto.builder()
                                .customerUseNo(parcel.customerUseNoBy(box))
                                .invoiceNo(box.getInvoiceNo())
                                .shippingType(REGISTER_SHIPPING_TYPE)
                                .contractCode(parcel.getDeliveryInfo().getContractCode())
                                .collectionDate(DateUtil.parseToStringDateYYYYMMDD(parcel.getDeliveryInfo().getPickDate()))
                                .senderZipCode(parcel.getSender().getAddress().getZipCode())
                                .senderAddress(parcel.getSender().getAddress().getAddress())
                                .senderAddressDetail(parcel.getSender().getAddress().getAddressDetail())
                                .senderName(parcel.getSender().getName())
                                .senderPhone(parcel.getSender().getPhone().getNumber())
                                .senderCellPhone(parcel.getSender().getPhone().getNumberEtc())
                                .receiverZipCode(parcel.getReceiver().getAddress().getZipCode())
                                .receiverAddress(parcel.getReceiver().getAddress().getAddress())
                                .receiverAddressDetail(parcel.getReceiver().getAddress().getAddressDetail())
                                .receiverName(parcel.getReceiver().getName())
                                .receiverPhone(parcel.getReceiver().getPhone().getNumber())
                                .receiverCellPhone(parcel.getReceiver().getPhone().getNumberEtc())
                                .shipRequestMemo(parcel.getMemo().getShipRequestMemo())
                                .displayProductName(box.displayItemName())
                                .boxQty(1)
                                .paymentType(PAYMENT_TYPE)
                                .boxType(box.hanjinBoxType())
                                .hanjinInputValue(parcel.getDeliveryInfo().getDeliveryType().getDescription())
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<CustomerUseNoDto> toCustomerUseNoDto(Parcel parcel) {
        return parcel.getBoxes()
                .stream()
                .map(box -> new CustomerUseNoDto(parcel.customerUseNoBy(box)))
                .collect(Collectors.toList());
    }
}


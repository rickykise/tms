package ai.fassto.tms.externalservice.parcel.chainlogis.mapper;

import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import ai.fassto.tms.domain.parcel.application.vo.Memo;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisInvoiceDataDto;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisRegisterRequestDto;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisRegisterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ChainLogisParcelDataMapper {
    public ChainLogisRegisterRequestDto toChainLogisRegisterRequestDto(Destination sender, Destination receiver, FmsInfo fmsInfo, Memo memo, Box box) {
        return ChainLogisRegisterRequestDto.builder()
                .receiverName(receiver.getName())
                .receiverMobile(receiver.getPhone().getNumber())
                .receiverAddress(receiver.getAddress().getAddress())
                .receiverAddressDetail(receiver.getAddress().getAddressDetail())
                .productName(box.displayItemName())
                .memoFromCustomer(memo.getShipRequestMemo())
                .orderIdFromCorp(fmsInfo.chainLogisOrderIdFromCorp())
                .frontdoorPassword(memo.getEntrancePassword())
                .etc1(fmsInfo.warehouseCode())
                .senderName(sender.getName())
                .senderMobile(sender.getPhone().getNumber())
                .customSenderAddress(sender.getAddress().getAddress())
                .customSenderAddressDetail(sender.getAddress().getAddressDetail())
                .build();
    }

    public ChainLogisInvoiceDataDto toChainLogisInvoiceData(ChainLogisRegisterResponseDto responseDto) {
        return ChainLogisInvoiceDataDto.builder()
                .invoiceNo(responseDto.bookId())
                .dongGroup(responseDto.dongGroup())
                .build();
    }
}

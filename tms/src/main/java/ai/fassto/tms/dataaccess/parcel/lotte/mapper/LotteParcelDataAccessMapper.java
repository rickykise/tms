package ai.fassto.tms.dataaccess.parcel.lotte.mapper;

import ai.fassto.tms.dataaccess.parcel.lotte.dto.RegisterParcelDto;
import ai.fassto.tms.dataaccess.parcel.lotte.dto.SelectRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.lotte.vo.LotteParcelConst;
import ai.fassto.tms.dataaccess.parcel.lotte.vo.LotteWorkStatusType;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static ai.fassto.tms.dataaccess.parcel.lotte.vo.LotteParcelConst.*;


@Component
public class LotteParcelDataAccessMapper {
    public List<SelectRegisteredInvoiceDto> toSelectRegisteredInvoiceDto(Parcel parcel) {
        return parcel.getBoxes()
                .stream()
                .map(box -> SelectRegisteredInvoiceDto.builder()
                        .wkSct(LotteWorkStatusType.REGISTER_ORDER.getCode())
                        .custUseNo(parcel.customerUseNoBy(box))
                        .superCustCd(LotteParcelConst.SUPER_CUST_CODE)
                        .build())
                .collect(Collectors.toList());
    }

    public List<RegisterParcelDto> toRegisterParcelDtos(Parcel parcel) {
        return parcel.getBoxes()
                .stream()
                .map(box -> RegisterParcelDto.builder()
                        .acptSseq(LOTTE_INVOICE_FIXED_ACPTSSEQ)
                        .wkSct(LotteWorkStatusType.REGISTER_ORDER.getCode())
                        .ordNo(parcel.customerUseNoBy(box))
                        .invNo(box.getInvoiceNo())
                        .superCustCd(LotteParcelConst.SUPER_CUST_CODE)
                        .custCd(parcel.getDeliveryInfo().getContractCode())
                        .orgInvNo(LOTTE_INVOICE_FIXED_ORGINAL_INV_NO)
                        .itemSize(LOTTE_INVOICE_FIXED_ITEM_SIZE)
                        .itemNm(LOTTE_INVOICE_FIXED_ITEM_NAME)
                        .qty(LOTTE_INVOICE_FIXED_QTY)
                        .dlvMsg(parcel.getMemo().getShipRequestMemo())
                        .acperNm(parcel.getReceiver().getName())
                        .acperTel(parcel.getReceiver().getPhone().getNumber())
                        .acperHtel(parcel.getReceiver().getPhone().getNumberEtc())
                        .acperZipNo(parcel.getReceiver().getAddress().getZipCode())
                        .acperAddr(parcel.getReceiver().getAddress().fullAddress())
                        .snperNm(parcel.getSender().getName())
                        .snperTel(parcel.getSender().getPhone().getNumber())
                        .snperHtel(parcel.getSender().getPhone().getNumberEtc())
                        .snperZipNo(parcel.getSender().getAddress().getZipCode())
                        .snperAddr(parcel.getSender().getAddress().fullAddress())
                        .conFlg(LOTTE_INVOICE_FIXED_CON_FLG)
                        .sumFare(LOTTE_INVOICE_FIXED_SUM_FARE)
                        .trsStatCd(LOTTE_INVOICE_FIXED_TRS_STAT_CD)
                        .build())
                .collect(Collectors.toList());
    }
}

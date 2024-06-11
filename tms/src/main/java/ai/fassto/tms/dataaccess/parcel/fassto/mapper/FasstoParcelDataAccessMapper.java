package ai.fassto.tms.dataaccess.parcel.fassto.mapper;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.*;
import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.vo.*;
import ai.fassto.tms.domain.parcel.application.vo.invoice.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FasstoParcelDataAccessMapper {
    private static final String HANJIN_PAY_CON = "CD";
    private static final String ECREMMOCE_FRT_DIV_CD = "03";
    private static final String ECREMMOCE_BOX_TYPE_CD = "01";
    private static final String CJ_FRT_DIV_CD = "03";

    public DeliveryUnavailableDto toDeliveryUnavailableDto(
            FmsInfo fmsInfo,
            DeliveryInfo deliveryInfo,
            Address receiverAddress
    ) {
        return DeliveryUnavailableDto.builder()
                .warehouseCode(fmsInfo.warehouseCode())
                .zipCode(receiverAddress.getZipCode())
                .parcelCompanyType(deliveryInfo.getParcelCompanyType())
                .build();
    }

    public SelectInvoiceInfoDto toSelectInvoiceInfo(DeliveryInfo deliveryInfo, FmsInfo fmsInfo) {
        return SelectInvoiceInfoDto.builder()
                .parcelCompanyType(deliveryInfo.getParcelCompanyType())
                .warehouseCode(fmsInfo.warehouseCode())
                .customerCode(fmsInfo.customerCode())
                .build();
    }

    public UpdateInvoiceInfoDto toUpdateInvoiceInfo(InvoiceInfoDto invoiceInfoDto, DeliveryInfo deliveryInfo) {
        return UpdateInvoiceInfoDto.builder()
                .nextInvoiceNo(invoiceInfoDto.nextInvoiceNo())
                .parcelCompanyType(deliveryInfo.getParcelCompanyType())
                .custId(invoiceInfoDto.custId())
                .build();
    }

    public UpdateTeamFreshOrderDto toUpdateTeamFreshOrderDto(
            Parcel parcel,
            TeamFreshInvoiceInfoDto teamFreshInvoiceInfoDto
    ) {
        return UpdateTeamFreshOrderDto.builder().teamFreshOrdNo(teamFreshInvoiceInfoDto.teamFreshOrdNo())
                .teamFreshOrdDt(teamFreshInvoiceInfoDto.teamFreshOrdDt())
                .areaCode(teamFreshInvoiceInfoDto.areaCode())
                .slipNo(parcel.getFmsInfo().slipNo())
                .build();
    }

    public SelectCenterContractCodeDto toSelectCenterContractCodeDto(FmsInfo fmsInfo, DeliveryInfo deliveryInfo) {
        return SelectCenterContractCodeDto.builder()
                .whCd(fmsInfo.warehouseCode())
                .parcelCompanyType(deliveryInfo.getParcelCompanyType())
                .customerCode(fmsInfo.customerCode())
                .build();
    }

    public SelectItemDto toSelectItemDto(Item item) {
        return SelectItemDto.builder()
                .code(item.getCode())
                .build();
    }

    public SelectSenderInfoDto toSelectSenderInfo(FmsInfo fmsInfo) {
        return SelectSenderInfoDto.builder()
                .slipNo(fmsInfo.slipNo())
                .warehouseCode(fmsInfo.warehouseCode())
                .customerCode(fmsInfo.customerCode())
                .orderNo(fmsInfo.orderNo())
                .build();
    }

    public List<HanjinInvoiceDataDto> toHanjinInvoiceDataDto(Parcel parcel) {
        final FmsInfo fmsInfo = parcel.getFmsInfo();
        final Destination sender = parcel.getSender();
        final Destination receiver = parcel.getReceiver();
        final HanjinInvoiceData hanjinInvoiceData = (HanjinInvoiceData) parcel.getInvoiceData();
        final Memo memo = parcel.getMemo();

        return parcel.getBoxes()
                .stream()
                .map(box -> HanjinInvoiceDataDto.builder()
                        .invcNo(box.getInvoiceNo())
                        .boxTyp(box.hanjinBoxType())
                        .boxQty(1)
                        .customerName(fmsInfo.customerName())
                        .sendrTelNo1(sender.getPhone().getPhoneBy(0))
                        .sendrTelNo2(sender.getPhone().getPhoneBy(1))
                        .sendrTelNo3(sender.getPhone().getPhoneBy(2))
                        .sendrAddr(sender.getAddress().getAddress())
                        .sendrDetailAddr(sender.getAddress().getAddressDetail())
                        .rcvrNm(receiver.getName())
                        .rcvrTelNo1(receiver.getPhone().getPhoneBy(0))
                        .rcvrTelNo2(receiver.getPhone().getPhoneBy(1))
                        .rcvrTelNo3(receiver.getPhone().getPhoneBy(2))
                        .rcvrTelEtcNo1(receiver.getPhone().getPhoneEtcBy(0))
                        .rcvrTelEtcNo2(receiver.getPhone().getPhoneEtcBy(1))
                        .rcvrTelEtcNo3(receiver.getPhone().getPhoneEtcBy(2))
                        .rcvrAddr(receiver.getAddress().getAddress())
                        .rcvrDetailAddr(receiver.getAddress().getAddressDetail())
                        .markGdsNm(box.invoiceMarkItemName())
                        .gdsNm(box.displayItemName())
                        .gdsQty(box.totalItemQty())
                        .hubCod(hanjinInvoiceData.getHubCod())
                        .rcvTmlNam(hanjinInvoiceData.getTmlNam())
                        .rcvTmlCod(hanjinInvoiceData.getTmlCod())
                        .cenCod(hanjinInvoiceData.getCenCod())
                        .cenNam(hanjinInvoiceData.getCenNam())
                        .domMid(hanjinInvoiceData.getDomMid())
                        .domPdz(hanjinInvoiceData.getGrpRnk())
                        .esNam(hanjinInvoiceData.getEsNam())
                        .sendTmlCod(hanjinInvoiceData.getSTmlCod())
                        .sendTmlNam(hanjinInvoiceData.getSTmlNam())
                        .pdzNam(hanjinInvoiceData.getEsCod())
                        .payCon(HANJIN_PAY_CON)
                        .domRgn(hanjinInvoiceData.getDomRgn())
                        .customerMemo(memo.getShipRequestMemo())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public OutOrdSlipNoDto toOutOrdSlipNoDto(Parcel parcel) {
        return OutOrdSlipNoDto.builder()
                .outOrdSlipNo(parcel.getFmsInfo().slipNo())
                .build();
    }

    public WarehouseCodeDto toWarehouseCodeDto(FmsInfo fmsInfo) {
        return WarehouseCodeDto.builder()
                .warehouseCode(fmsInfo.warehouseCode())
                .build();
    }

    public InvoiceData toTeamFreshInvoiceData(TeamFreshOrderInfoDto orderInfoDto, WarehouseNameDto warehouseNameDto) {
        return TeamFreshInvoiceData.builder()
                .teamfreshOrdNo(orderInfoDto.teamfreshOrdNo())
                .areaCd(orderInfoDto.areaCd())
                .warehouseName(warehouseNameDto.whNm())
                .build();
    }

    public SelectEcremmoceParcelDataDto toSelectEcremmoceParcelDataDto(FmsInfo fmsInfo) {
        return SelectEcremmoceParcelDataDto.builder()
                .warehouseCode(fmsInfo.warehouseCode())
                .customerCode(fmsInfo.customerCode())
                .orderNo(fmsInfo.orderNo())
                .build();
    }

    public InvoiceData toEcremmoceInvoiceData(EcremmoceParcelDataDto ecremmoceParcelDataDto) {
        return EcremmoceInvoiceData.builder()
                .logiCenter(ecremmoceParcelDataDto.logiCenter())
                .platform(ecremmoceParcelDataDto.platform())
                .build();
    }

    /**
     * 이크레모스는 한 주문에 박스가 여러개여도 같은 송장을 쓰기 때문에
     * tb_invoice_print_ecremmoce 테이블에도 1개 열에만 해당 주문건에 대한 송장번호를 insert 함
     */
    public EcremmoceInvoiceDataDto toEcremmoceInvoiceDataDto(Parcel parcel) {
        return EcremmoceInvoiceDataDto.builder()
                .invcNo(parcel.getEcremmoceInvoice())
                .ordNo(parcel.getFmsInfo().orderNo())
                .rcptYmd(parcel.getDeliveryInfo().getPickDate().toString())
                .frtDvCd(ECREMMOCE_FRT_DIV_CD)
                .boxTypeCd(ECREMMOCE_BOX_TYPE_CD)
                .boxQty(parcel.getBoxes().boxSize())
                .sendrNm(parcel.getSender().getName())
                .sendrTelNo(parcel.getSender().getPhone().getNumber())
                .rcvrNm(parcel.getReceiver().getName())
                .rcvrTelNo(parcel.getReceiver().getPhone().getNumber())
                .rcvrAddr(parcel.getReceiver().getAddress().fullAddress())
                .markGdsNm(parcel.getBoxes().fistBoxInvoiceMarkItemName())
                .gdsNm(parcel.getBoxes().firstBoxDisplayItemName())
                .gdsQty(parcel.getBoxes().totalItemQty())
                .platform(((EcremmoceInvoiceData) parcel.getInvoiceData()).getPlatform())
                .logiCenter(((EcremmoceInvoiceData) parcel.getInvoiceData()).getLogiCenter())
                .build();
    }

    public List<ChainLogisInvoiceDataDto> toChainLogisInvoiceDataDto(Parcel parcel) {
        return parcel.getBoxes().stream()
                .map(box -> ChainLogisInvoiceDataDto.builder()
                        .invoiceNo(box.getInvoiceNo())
                        .boxQty(1)
                        .sendrNm(parcel.getSender().getName())
                        .sendrTelNo1(parcel.getSender().getPhone().getPhoneBy(0))
                        .sendrTelNo2(parcel.getSender().getPhone().getPhoneBy(1))
                        .sendrTelNo3(parcel.getSender().getPhone().getPhoneBy(2))
                        .sendrAddr(parcel.getSender().getAddress().getAddress())
                        .sendrDetailAddr(parcel.getSender().getAddress().getAddressDetail())
                        .rcvrNm(parcel.getReceiver().getName())
                        .rcvrTelNo1(parcel.getReceiver().getPhone().getPhoneBy(0))
                        .rcvrTelNo2(parcel.getReceiver().getPhone().getPhoneBy(1))
                        .rcvrTelNo3(parcel.getReceiver().getPhone().getPhoneBy(2))
                        .rcvrTelEtcNo1(parcel.getReceiver().getPhone().getPhoneEtcBy(0))
                        .rcvrTelEtcNo2(parcel.getReceiver().getPhone().getPhoneEtcBy(1))
                        .rcvrTelEtcNo3(parcel.getReceiver().getPhone().getPhoneEtcBy(2))
                        .rcvrAddr(parcel.getReceiver().getAddress().getAddress())
                        .rcvrDetailAddr(parcel.getReceiver().getAddress().getAddressDetail())
                        .markGdsNm(box.invoiceMarkItemName())
                        .gdsNm(box.displayItemName())
                        .gdsQty(box.totalItemQty())
                        .dongGroup(((ChainLogisInvoiceData) parcel.getInvoiceData()).getDongGroup())
                        .customerMemo(parcel.getMemo().getShipRequestMemo())
                        .build())
                .collect(Collectors.toList());
    }

    public List<LotteInvoiceDataDto> toLotteInvoiceDataDtos(Parcel parcel) {
        final LotteInvoiceData invoiceData = (LotteInvoiceData) parcel.getInvoiceData();

        return parcel.getBoxes().stream()
                .map(box -> LotteInvoiceDataDto.builder()
                        .invcNo(box.getInvoiceNo())
                        .conFlg(invoiceData.getConFlg())
                        .sendrNm(parcel.getSender().getName())
                        .sendrTelNo1(parcel.getSender().getPhone().getPhoneBy(0))
                        .sendrTelNo2(parcel.getSender().getPhone().getPhoneBy(1))
                        .sendrTelNo3(parcel.getSender().getPhone().getPhoneBy(2))
                        .sendrAddr(parcel.getSender().getAddress().fullAddress())
                        .rcvrNm(parcel.getReceiver().getName())
                        .rcvrTelNo1(parcel.getReceiver().getPhone().getPhoneBy(0))
                        .rcvrTelNo2(parcel.getReceiver().getPhone().getPhoneBy(1))
                        .rcvrTelNo3(parcel.getReceiver().getPhone().getPhoneBy(2))
                        .rcvrTelEtcNo1(parcel.getReceiver().getPhone().getPhoneEtcBy(0))
                        .rcvrTelEtcNo2(parcel.getReceiver().getPhone().getPhoneEtcBy(1))
                        .rcvrTelEtcNo3(parcel.getReceiver().getPhone().getPhoneEtcBy(2))
                        .rcvrAddr(parcel.getReceiver().getAddress().fullAddress())
                        .markGdsNm(box.invoiceMarkItemName())
                        .gdsNm(box.displayItemName())
                        .gdsQty(box.totalItemQty())
                        .sumFare(box.getFare().getSumFare())
                        .tmlCd(invoiceData.getTmlCd())
                        .tmlNm(invoiceData.getTmlNm())
                        .cityGunGu(invoiceData.getCityGunGu())
                        .eupMuinDong(invoiceData.getEupMuinDong())
                        .filtCd(invoiceData.getFiltCd())
                        .brnclctCd(invoiceData.getBrnclctCd())
                        .brnclctNm(invoiceData.getBrnclctNm())
                        .brnclctTelNo(invoiceData.getBrnclctTelNo())
                        .brnshpNm(invoiceData.getBrnshpNm())
                        .dlvMsg(parcel.getMemo().getShipRequestMemo())
                        .empNm(invoiceData.getEmpNm())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CjInvoiceDataDto> toCjInvoiceDataDtos(Parcel parcel) {
        final CjInvoiceData invoiceData = (CjInvoiceData) parcel.getInvoiceData();

        return parcel.getBoxes().stream()
                .map(box -> CjInvoiceDataDto.builder()
                        .invcNo(box.getInvoiceNo())
                        .frtDvCd(CJ_FRT_DIV_CD)
                        .boxTypeCd(box.getCjBoxType())
                        .boxQty(1)
                        .sendrNm(parcel.getSender().getName())
                        .sendrTelNo1(parcel.getSender().getPhone().getPhoneBy(0))
                        .sendrTelNo2(parcel.getSender().getPhone().getPhoneBy(1))
                        .sendrTelNo3(parcel.getSender().getPhone().getPhoneBy(2))
                        .sendrAddr(parcel.getSender().getAddress().getAddress())
                        .sendrDetailAddr(parcel.getSender().getAddress().getAddressDetail())
                        .rcvrNm(parcel.getReceiver().getName())
                        .rcvrTelNo1(parcel.getReceiver().getPhone().getPhoneBy(0))
                        .rcvrTelNo2(parcel.getReceiver().getPhone().getPhoneBy(1))
                        .rcvrTelNo3(parcel.getReceiver().getPhone().getPhoneBy(2))
                        .rcvrTelEtcNo1(parcel.getReceiver().getPhone().getPhoneEtcBy(0))
                        .rcvrTelEtcNo2(parcel.getReceiver().getPhone().getPhoneEtcBy(1))
                        .rcvrTelEtcNo3(parcel.getReceiver().getPhone().getPhoneEtcBy(2))
                        .rcvrAddr(parcel.getReceiver().getAddress().getAddress())
                        .rcvrDetailAddr(parcel.getReceiver().getAddress().getAddressDetail())
                        .markGdsNm(box.invoiceMarkItemName())
                        .gdsNm(box.displayItemName())
                        .gdsQty(box.totalItemQty())
                        .endNo(invoiceData.getEndNo())
                        .subEndNo(invoiceData.getSubEndNo())
                        .manBranNm(invoiceData.getManBranNm())
                        .cldvEmpNm(invoiceData.getCldvEmpNm())
                        .cldvEmpClsCd(invoiceData.getCldvEmpClsCd())
                        .shortAddr(invoiceData.getShortAddr())
                        .build())
                .collect(Collectors.toList());
    }

    public SelectLotteBrnclctDto toSelectLotteBrnclctDto(FmsInfo fmsInfo, DeliveryInfo deliveryInfo) {
        return SelectLotteBrnclctDto.builder()
                .deliveryCompanyCode(deliveryInfo.getParcelCompanyType().getFmsCode())
                .warehouseCode(fmsInfo.warehouseCode())
                .build();
    }
}

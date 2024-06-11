package ai.fassto.tms.externalservice.parcel.teamfresh.mapper;

import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.domain.parcel.application.vo.invoice.TeamFreshInvoiceData;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.BoxAddInvoiceNoDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.request.BoxAddRequestDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.request.RequestParcelInfoDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.request.TeamFreshRegisterParcelRequestDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.response.BoxAddResponseDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.response.TeamFreshRegisterParcelResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TeamFreshParcelDataMapper {
    public TeamFreshRegisterParcelRequestDto toTeamFreshRegisterParcelRequestDto(Parcel parcel) {
        return TeamFreshRegisterParcelRequestDto.builder()
                .orderDate(parcel.getDeliveryInfo().getPickDate())
                .orderInfo(
                        Collections.singletonList(
                                RequestParcelInfoDto.builder()
                                        .receiverName(parcel.getReceiver().getName())
                                        .receiverHp(parcel.getReceiver().getPhone().getNumber())
                                        .addrBasic(parcel.getReceiver().getAddress().getAddress())
                                        .addrDetail(parcel.getReceiver().getAddress().getAddressDetail())
                                        .zipCode(parcel.getReceiver().getAddress().getZipCode())
                                        .note1(parcel.getMemo().getEntrancePassword())
                                        .note2(parcel.getMemo().getShipRequestMemo())
                                        .boxCount(1)
                                        .customerOrderNum(parcel.getFmsInfo().orderNo())
                                        .senderName(parcel.getSender().getName())
                                        .build()
                        )
                )
                .build();
    }

    public TeamFreshInvoiceInfoDto toTeamFreshInvoiceInfoDto(TeamFreshRegisterParcelResponseDto responseDto, DeliveryInfo deliveryInfo) {
        return TeamFreshInvoiceInfoDto.builder()
                .teamFreshOrdNo(responseDto.getTeamFreshOrdNoBy(0))
                .teamFreshOrdDt(deliveryInfo.getPickDate())
                .areaCode(responseDto.getAreaCodeBy(0))
                .invoiceNumber(responseDto.getBarcodeNumBy(0, 0))
                .build();
    }

    public BoxAddRequestDto toBoxAddRequestDto(InvoiceData invoiceData) {
        return BoxAddRequestDto.builder()
                .orderNum(((TeamFreshInvoiceData) invoiceData).getTeamfreshOrdNo())
                .build();
    }

    public BoxAddInvoiceNoDto toBoxAddInvoiceNoDto(BoxAddResponseDto responseDto) {
        return BoxAddInvoiceNoDto.builder()
                .invoiceNo(responseDto.barcodeNum())
                .build();
    }
}

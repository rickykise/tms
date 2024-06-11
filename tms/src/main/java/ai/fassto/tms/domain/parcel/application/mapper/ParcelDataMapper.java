package ai.fassto.tms.domain.parcel.application.mapper;

import ai.fassto.tms.common.util.DateUtil;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.FinalCallBoxDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.FinalCallItemDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.PackageDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.PrintedDocumentDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;
import ai.fassto.tms.domain.parcel.application.vo.*;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.application.vo.enums.SoloChainShippingServiceType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParcelDataMapper {
    public Parcel toPreCallParcel(ParcelPreCallRequestDto request) {
        final FmsInfo fmsInfo = FmsInfo.builder()
                .slipNo(request.slipNo())
                .warehouseCode(request.warehouseCode())
                .customerCode(request.customerCode())
                .customerName(request.customerName())
                .orderNo(request.orderNo())
                .build();

        final DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .parcelCompanyType(ParcelCompanyType.DeliveryCompanyCodeBy(request.deliveryCompanyCode()))
                .deliveryType(SoloChainShippingServiceType.findBy(request.deliveryType()))
                .changingShippingOption(request.changingShippingOption())
                .pickDate(DateUtil.parseToLocalDate(request.pickDate()))
                .build();

        final Destination sender = Destination.builder()
                .name(request.senderName())
                .phone(
                        Phone.builder()
                                .number(request.senderPhone())
                                .numberEtc(request.senderPhoneEtc())
                                .build()
                )
                .address(
                        Address.builder()
                                .zipCode(request.senderZipCode())
                                .address(request.senderAddress())
                                .addressDetail(request.senderAddressDetail())
                                .build()
                ).build();

        final Destination receiver = Destination.builder()
                .name(request.receiverName())
                .phone(
                        Phone.builder()
                                .number(request.receiverPhone())
                                .numberEtc(request.receiverPhoneEtc())
                                .build()
                )
                .address(
                        Address.builder()
                                .zipCode(request.receiverZipCode())
                                .address(request.receiverAddress())
                                .addressDetail(request.receiverAddressDetail())
                                .build()
                ).build();

        final Memo memo = Memo.builder()
                .shipRequestMemo(request.shipRequestMemo())
                .entrancePassword(request.entrancePassword())
                .build();

        final List<Box> boxList = Collections.singletonList(Box.builder().invoiceNo(
                request.boxes() != null ? request.boxes().get(0).invoiceNo() : null
        ).build());

        return Parcel.builder()
                .fmsInfo(fmsInfo)
                .deliveryInfo(deliveryInfo)
                .sender(sender)
                .receiver(receiver)
                .memo(memo)
                .boxes(new Boxes(boxList))
                .build();
    }

    public ParcelPreCallResponseDto toParcelPreCallResponseDto(Parcel parcel) {
        return new ParcelPreCallResponseDto(parcel.getFmsInfo().slipNo(), parcel.getPreCallInvoice());
    }


    public Parcel toFinalCallParcel(ParcelFinalCallRequestDto request) {
        final FmsInfo fmsInfo = FmsInfo.builder()
                .slipNo(request.slipNo())
                .warehouseCode(request.warehouseCode())
                .customerCode(request.customerCode())
                .customerName(request.customerName())
                .orderNo(request.orderNo())
                .build();

        final DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .parcelCompanyType(ParcelCompanyType.DeliveryCompanyCodeBy(request.deliveryCompanyCode()))
                .deliveryType(SoloChainShippingServiceType.findBy(request.deliveryType()))
                .changingShippingOption(request.changingShippingOption())
                .pickDate(DateUtil.parseToLocalDate(request.pickDate()))
                .build();

        final Destination sender = Destination.builder()
                .name(request.senderName())
                .phone(
                        Phone.builder()
                                .number(request.senderPhone())
                                .numberEtc(request.senderPhoneEtc())
                                .build()
                )
                .address(
                        Address.builder()
                                .zipCode(request.senderZipCode())
                                .address(request.senderAddress())
                                .addressDetail(request.senderAddressDetail())
                                .build()
                ).build();

        final Destination receiver = Destination.builder()
                .name(request.receiverName())
                .phone(
                        Phone.builder()
                                .number(request.receiverPhone())
                                .numberEtc(request.receiverPhoneEtc())
                                .build()
                )
                .address(
                        Address.builder()
                                .zipCode(request.receiverZipCode())
                                .address(request.receiverAddress())
                                .addressDetail(request.receiverAddressDetail())
                                .build()
                ).build();

        final Memo memo = Memo.builder()
                .shipRequestMemo(request.shipRequestMemo())
                .entrancePassword(request.entrancePassword())
                .build();

        final List<Box> boxList = request.boxes().stream()
                .map(this::toFinalCallBox)
                .collect(Collectors.toList());

        return Parcel.builder()
                .fmsInfo(fmsInfo)
                .deliveryInfo(deliveryInfo)
                .sender(sender)
                .receiver(receiver)
                .memo(memo)
                .boxes(new Boxes(boxList))
                .build();
    }

    private Box toFinalCallBox(FinalCallBoxDto finalCallBoxDto) {
        final List<Item> itemList = finalCallBoxDto.items().stream()
                .map(this::toFinalCallItem)
                .collect(Collectors.toList());

        return Box.builder()
                .boxID(finalCallBoxDto.boxID())
                .invoiceNo(finalCallBoxDto.invoiceNo())
                .fasstoBoxType(finalCallBoxDto.type())
                .fasstoBoxCategory(finalCallBoxDto.category())
                .boxWidth(finalCallBoxDto.boxWidth())
                .boxHeight(finalCallBoxDto.boxHeight())
                .boxDepth(finalCallBoxDto.boxDepth())
                .boxWeight(finalCallBoxDto.boxWeight())
                .productQty(finalCallBoxDto.productQty())
                .items(new Items(itemList))
                .build();
    }

    private Item toFinalCallItem(FinalCallItemDto finalCallItemDto) {
        return Item.builder()
                .code(finalCallItemDto.code())
                .qty(finalCallItemDto.qty())
                .build();
    }

    public ParcelFinalCallResponseDto toParcelFinalCallResponseDto(Parcel parcel) {
        return ParcelFinalCallResponseDto.builder()
                .warehouseCode(parcel.getFmsInfo().warehouseCode())
                .customerCode(parcel.getFmsInfo().customerCode())
                .customerName(parcel.getFmsInfo().customerName())
                .deliveryCompanyCode(parcel.getDeliveryInfo().getParcelCompanyType().getWmsCode())
                .deliveryType(parcel.getDeliveryInfo().getDeliveryType().getCode())
                .changingShippingOption(parcel.getDeliveryInfo().getChangingShippingOption())
                .orderNo(parcel.getFmsInfo().orderNo())
                .slipNo(parcel.getFmsInfo().slipNo())
                .pickDate(DateUtil.parseToStringDateYYYYMMDD(parcel.getDeliveryInfo().getPickDate()))
                .senderName(parcel.getSender().getName())
                .senderPhone(parcel.getSender().getPhone().getNumber())
                .senderPhoneEtc(parcel.getSender().getPhone().getNumberEtc())
                .senderZipCode(parcel.getSender().getAddress().getZipCode())
                .senderAddress(parcel.getSender().getAddress().getAddress())
                .senderAddressDetail(parcel.getSender().getAddress().getAddressDetail())
                .receiverName(parcel.getReceiver().getName())
                .receiverPhone(parcel.getReceiver().getPhone().getNumber())
                .receiverPhoneEtc(parcel.getReceiver().getPhone().getNumberEtc())
                .receiverZipCode(parcel.getReceiver().getAddress().getZipCode())
                .receiverAddress(parcel.getReceiver().getAddress().getAddress())
                .receiverAddressDetail(parcel.getReceiver().getAddress().getAddressDetail())
                .shipRequestMemo(parcel.getMemo().getShipRequestMemo())
                .entrancePassword(parcel.getMemo().getEntrancePassword())
                .packages(toListPackageDto(parcel))
                .build();
    }

    private List<PackageDto> toListPackageDto(Parcel parcel) {
        final List<PackageDto> packageDtoList = new ArrayList<>();

        for (Box box : parcel.getBoxes().boxes()) {
            packageDtoList.add(PackageDto.builder()
                    .boxID(box.getBoxID())
                    .invoiceNumber(box.getInvoiceNo())
                    .basicFare(box.getFare().getBasicFare())
                    .dealFare(box.getFare().getDealFare())
                    .airFare(box.getFare().getAirFare())
                    .shipFare(box.getFare().getShipFare())
                    .boxType(box.getFasstoBoxType())
                    .boxWidth(box.getBoxWidth())
                    .boxHeight(box.getBoxHeight())
                    .boxDepth(box.getBoxDepth())
                    .boxWeight(box.getBoxWeight())
                    .printDocument(toPrintedDocumentDto(parcel, box))
                    .build());
        }

        return packageDtoList;
    }


    private PrintedDocumentDto toPrintedDocumentDto(Parcel parcel, Box box) {
        return PrintedDocumentDto.builder()
                .documentName(parcel.documentName())
                .encodedContents(parcel.zplInvoiceCode(box))
                .build();
    }
}

package ai.fassto.tms.externalservice.parcel.sweettracker.mapper;

import ai.fassto.tms.domain.parcel.application.vo.Boxes;
import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import ai.fassto.tms.externalservice.parcel.sweettracker.dto.TrackingParcelRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SweetTrackerParcelApiDataMapper {
    public List<TrackingParcelRequestDto> toTrackingParcelRequestDto(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Boxes boxes) {
        return boxes.boxes()
                .stream()
                .map(box -> TrackingParcelRequestDto.builder()
                        .cstCd(fmsInfo.customerCode())
                        .whCd(fmsInfo.warehouseCode())
                        .invoiceNo(box.getInvoiceNo())
                        .parcelCd(deliveryInfo.getParcelCompanyType().getFmsCode())
                        .build())
                .collect(Collectors.toList());
    }
}

package ai.fassto.tms.externalservice.parcel.sweettracker.dto;

import lombok.Builder;

@Builder
public record TrackingParcelRequestDto(
        String cstCd,
        String whCd,
        String invoiceNo,
        String parcelCd
) {
}

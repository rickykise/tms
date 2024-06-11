package ai.fassto.tms.externalservice.parcel.cj.dto;

import lombok.Builder;

@Builder
public record CjAddrRequestParamDto(
        String senderAddr,
        String receiverAddr,
        String boxType) {
}

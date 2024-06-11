package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record SenderInfoDto(
        String senderName,
        String senderTelNo
) {
}

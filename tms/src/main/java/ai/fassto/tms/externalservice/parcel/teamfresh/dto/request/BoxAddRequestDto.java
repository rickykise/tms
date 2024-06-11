package ai.fassto.tms.externalservice.parcel.teamfresh.dto.request;

import lombok.Builder;

@Builder
public record BoxAddRequestDto(
        String orderNum
) {
}

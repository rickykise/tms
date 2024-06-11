package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateTeamFreshOrderDto(
        String teamFreshOrdNo,
        LocalDate teamFreshOrdDt,
        String areaCode,
        String slipNo
) {
}

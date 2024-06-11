package ai.fassto.tms.externalservice.parcel.teamfresh.dto.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public record TeamFreshRegisterParcelRequestDto(
        LocalDate orderDate,
        List<RequestParcelInfoDto> orderInfo
) {
    @Builder
    public TeamFreshRegisterParcelRequestDto(LocalDate orderDate, List<RequestParcelInfoDto> orderInfo) {
        this.orderDate = orderDate == null ? LocalDate.now() : orderDate;
        this.orderInfo = orderInfo;
    }
}

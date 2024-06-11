package ai.fassto.tms.domain.parcel.application.dto.teamfresh;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public record TeamFreshInvoiceInfoDto(
        String teamFreshOrdNo,
        LocalDate teamFreshOrdDt,
        String areaCode,
        String invoiceNumber
) {
}

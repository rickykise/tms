package ai.fassto.tms.externalservice.parcel.teamfresh.dto;

import lombok.Builder;

@Builder
public record BoxAddInvoiceNoDto(
        String invoiceNo
) {
}

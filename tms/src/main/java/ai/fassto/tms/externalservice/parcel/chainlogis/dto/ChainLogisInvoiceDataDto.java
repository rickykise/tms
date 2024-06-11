package ai.fassto.tms.externalservice.parcel.chainlogis.dto;

import lombok.Builder;

@Builder
public record ChainLogisInvoiceDataDto(
        String invoiceNo,
        String dongGroup
) {
}

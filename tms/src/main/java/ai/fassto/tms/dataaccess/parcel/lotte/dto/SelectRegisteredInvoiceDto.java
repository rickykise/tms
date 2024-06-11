package ai.fassto.tms.dataaccess.parcel.lotte.dto;

import lombok.Builder;

@Builder
public record SelectRegisteredInvoiceDto(
        String wkSct,
        String superCustCd,
        String custUseNo
) {
}

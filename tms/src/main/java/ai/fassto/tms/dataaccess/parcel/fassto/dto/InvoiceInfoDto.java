package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record InvoiceInfoDto(
        String nextInvoiceNo,
        String invoiceNo,
        String custId,
        String endInvoiceNo,
        String strInvoiceNo
) {

    //송장대역대 초과여부
    public boolean isOverInvoiceNo() {
        if (Long.parseLong(endInvoiceNo) < Long.parseLong(nextInvoiceNo)){
            return true;
        }

        if (Long.parseLong(strInvoiceNo) > Long.parseLong(nextInvoiceNo)){
            return true;
        }

        return false;
    }
}

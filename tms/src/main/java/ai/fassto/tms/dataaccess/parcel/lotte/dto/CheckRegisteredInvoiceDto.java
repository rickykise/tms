package ai.fassto.tms.dataaccess.parcel.lotte.dto;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.core.exception.DuplicateInvoiceException;

public record CheckRegisteredInvoiceDto(
        boolean isRegisteredInvoice
) {
    public void checkRegisteredInvoice() {
        if (isRegisteredInvoice) {
            throw new DuplicateInvoiceException(TmsResultType.DUPLICATE_INVOICE);
        }
    }
}

package ai.fassto.tms.domain.parcel.core.exception;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import lombok.Getter;

@Getter
public class InvoiceNotCollectException extends RuntimeException {
    private final TmsResultType tmsResultType;

    public InvoiceNotCollectException(TmsResultType tmsResultType) {
        super(tmsResultType.getMessage());
        this.tmsResultType = tmsResultType;
    }

    public InvoiceNotCollectException(TmsResultType tmsResultType, String customMessage) {
        super(customMessage);
        this.tmsResultType = tmsResultType;
    }
}

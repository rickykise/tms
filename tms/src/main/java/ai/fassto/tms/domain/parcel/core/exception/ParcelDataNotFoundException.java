package ai.fassto.tms.domain.parcel.core.exception;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import lombok.Getter;

@Getter
public class ParcelDataNotFoundException extends RuntimeException {
    private final TmsResultType tmsResultType;

    public ParcelDataNotFoundException(TmsResultType tmsResultType) {
        super(tmsResultType.getMessage());
        this.tmsResultType = tmsResultType;
    }

    public ParcelDataNotFoundException(TmsResultType tmsResultType, String customMessage) {
        super(customMessage);
        this.tmsResultType = tmsResultType;
    }
}

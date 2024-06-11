package ai.fassto.tms.domain.parcel.core.exception;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {
    private final TmsResultType tmsResultType;


    public ItemNotFoundException(TmsResultType tmsResultType) {
        super(tmsResultType.getMessage());
        this.tmsResultType = tmsResultType;
    }

    public ItemNotFoundException(TmsResultType tmsResultType, String customMessage) {
        super(customMessage);
        this.tmsResultType = tmsResultType;
    }
}

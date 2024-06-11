package ai.fassto.tms.dataaccess.common.vo.enums;

import lombok.Getter;

@Getter
public enum TmsApiType {
    PRE_CALL("PRE_CALL"), FINAL_CALL("FINAL_CALL");

    private final String type;

    TmsApiType(String type) {
        this.type = type;
    }
}

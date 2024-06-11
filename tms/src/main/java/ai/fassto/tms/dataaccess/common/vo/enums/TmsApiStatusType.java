package ai.fassto.tms.dataaccess.common.vo.enums;

import lombok.Getter;

@Getter
public enum TmsApiStatusType {
    SUCCESS("SUCCESS"), IN_PROGRESS("IN_PROGRESS"), FAIL("FAIL");

    private final String status;

    TmsApiStatusType(String status) {
        this.status = status;
    }
}

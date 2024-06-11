package ai.fassto.tms.dataaccess.parcel.lotte.vo;

import lombok.Getter;

@Getter
public enum LotteWorkStatusType {
    REGISTER_ORDER("001", "출고"),
    RETURN_ORDER("002", "반품");

    private final String code;
    private final String name;

    LotteWorkStatusType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

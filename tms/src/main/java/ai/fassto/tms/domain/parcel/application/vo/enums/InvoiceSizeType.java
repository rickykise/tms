package ai.fassto.tms.domain.parcel.application.vo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum InvoiceSizeType {
    INTEGRATED_INVOICE("INTEGRATED", "127mm x 103mm"),
    CHAIN_LOGIS_INVOICE("CHAINLOGIS", "127mm x 103mm"),
    ECREMMOCE_INVOICE("ECREMMOCE", "103mm x 103mm"),
    LOTTE_INVOICE("LOTTE", "203mm x 105mm");

    private final String fmsCode;
    private final String size;

    InvoiceSizeType(String fmsCode, String size) {
        this.fmsCode = fmsCode;
        this.size = size;
    }

    public static InvoiceSizeType findBy(String fmsCode) {
        return Arrays.stream(values())
                .filter(type -> type.fmsCode.equals(fmsCode))
                .findFirst()
                .orElse(InvoiceSizeType.INTEGRATED_INVOICE);
    }
}

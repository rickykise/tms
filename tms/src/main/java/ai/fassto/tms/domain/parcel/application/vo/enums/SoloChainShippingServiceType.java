package ai.fassto.tms.domain.parcel.application.vo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SoloChainShippingServiceType {
    HANJIN_NORMAL("한진택배-일반", "Hanjin-Normal", ""),
    HANJIN_24H("한진택배-24시출고", "Hanjin-24H", ""),
    HANJIN_GUARANTEE("한진택배-도착보장", "Hanjin-Guarantee", "N도착"),
    HANJIN_SPECIAL("한진택배-특화센터납", "Hanjin-Special", ""),
    HANJIN_URGENT("한진택배-긴급", "Hanjin-Urgent", "긴급"),

    CJ_NORMAL("CJ대한통운-일반", "CJ-Normal", ""),
    CJ_24H("CJ대한통운-24시출고", "CJ-24H", ""),
    CJ_GUARANTEE("CJ대한통운-도착보장", "CJ-Guarantee", ""),
    CJ_SPECIAL("CJ대한통운-특화센터납", "CJ-Special", ""),

    LOTTE_NORMAL("롯데택배-일반", "Lotte-Normal", ""),
    LOTTE_SPECIAL("롯데-특화센터납", "Lotte-Special", ""),

    TEAMFRESH_MORNING("팀프레시-새벽배송", "Teamfresh-Morning", ""),

    CHAINLOGIS_TODAY("체인로지스-당일배송", "Chainlogis-Today", ""),

    BAROGO_TODAY("바로고-당일배송", "Barogo-Today", ""),

    YLP_F_NORMAL("YLP-차량일반", "YLP-Normal", ""),

    FM_F_NORMAL("파스토모빌리티-차량일반", "FM-F-Normal", ""),

    SAGAWA_OVERSEAS("사가와-해외", "Sagawa-Overseas", ""),

    ECMS_OVERSEAS("이크레모스-해외", "ECMS-Overseas", ""),

    LINCOS_OVERSEAS("린코스-해외", "Lincos-Overseas", ""),

    EFS_OVERSEAS("EFS-해외", "EFS-Overseas", ""),

    FEDEX_OVERSEAS("페덱스-해외", "Fedex-Overseas", ""),

    UPS_OVERSEAS("UPS-해외", "UPS-Overseas", ""),

    SL_CORPORATE("기업택배", "Corporate-Logistics", ""),

    COMMERCE_OVERSEAS("Standard(기타) 대응", "Commerce-Overseas", ""),

    VISIT_PICK("방문수령", "Visit-pick", ""),

    NOT_REGISTER("등록되지 않은 서비스레벨", "NOT_REGISTER", "");

    private final String level;
    private final String code;
    private final String description;

    SoloChainShippingServiceType(String level, String code, String description) {
        this.level = level;
        this.code = code;
        this.description = description;
    }

    public static SoloChainShippingServiceType findBy(String code) {
        return Arrays.stream(values())
                .filter(level -> level.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(NOT_REGISTER);
    }
}

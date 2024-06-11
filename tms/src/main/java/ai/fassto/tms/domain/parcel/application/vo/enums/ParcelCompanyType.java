package ai.fassto.tms.domain.parcel.application.vo.enums;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.core.exception.ParcelCompanyNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ParcelCompanyType {
    CJ("CJ", "CJ", "CJ대한통운"),
    HANJIN("HANJIN", "Hanjin", "한진택배"),
    LOTTE("LOTTE", "Lotte", "롯데택배"),
    TEAMFRESH("TEAMFRESH", "Teamfresh", "팀프레시"),
    CHAINLOGIS("CHAINLOGIS", "Chain Logis", "체인로지스"),
    ECREMMOCE("ECREMMOCE", "Ecremmoce", "이크레모스");

    // 파스토 내부적으로 사용하는 풀 대문자 코드
    private final String fmsCode;
    // 솔로체인에서 내려주는 택배사 코드
    private final String wmsCode;
    private final String desc;

    ParcelCompanyType(String fmsCode, String wmsCode, String desc) {
        this.fmsCode = fmsCode;
        this.wmsCode = wmsCode;
        this.desc = desc;
    }


    public static ParcelCompanyType DeliveryCompanyCodeBy(String wmsCode) {
        return Arrays.stream(values())
                .filter(v -> v.wmsCode.equalsIgnoreCase(wmsCode))
                .findFirst()
                .orElseThrow(() -> new ParcelCompanyNotFoundException(TmsResultType.ILLEGAL_PARCEL_COMPANY, "등록되지 않은 택배사 코드입니다."));
    }
    }

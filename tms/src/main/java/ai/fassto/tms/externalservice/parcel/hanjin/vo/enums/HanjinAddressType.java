package ai.fassto.tms.externalservice.parcel.hanjin.vo.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
public enum HanjinAddressType {
    OK("OK", "정상 처리 되었습니다."),
    ERROR_01("ERROR-01", "출발지 우편번호가 잘못되었습니다."),
    ERROR_02("ERROR-02", "배송지 우편번호가 잘못되었습니다."),
    ERROR_03("ERROR-03", "유효하지 않은 고객번호(거래중지)"),
    ERROR_04("ERROR-04", "배송지 주소가 잘못되었습니다."),
    ERROR_05("ERROR-05", "일일 운송장 출력 한도를 초과했습니다."),
    ERROR_06("ERROR-06", "운송장 출력이 통제되는 지역입니다."),
    ERROR_07("ERROR-99", "기타 오류가 발생하였습니다.");

    private final String code;
    private final String message;

    HanjinAddressType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static boolean isSuccess(String resultCode) {
        return OK.code.equals(resultCode);
    }

    public static boolean isStopTrading(String resultCode) {
        return ERROR_03.code.equals(resultCode);
    }

    public static HanjinAddressType findBy(String resultCode) {
        return Arrays.stream(values())
                .filter(v -> v.code.equals(resultCode))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("한진 에러 코드를 확인할 수 없습니다."));
    }
}

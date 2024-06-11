package ai.fassto.tms.externalservice.parcel.cj.vo;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.core.exception.AddressCheckFailException;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * - 0 : 정제에 성공한 상태입니다.
 * - 20000 : 입력파라미터 중 코드 값이 잘못되어 있을 발생하는 오류입니다.
 * - 20001 : CJ대한통운에 등록되지 않은 고객ID입니다.
 * - 20002 : 입력하신 주소에 대한 분석에 실패한 경우입니다.
 * - 20003 : 입력하신 주소에 대해 집배권역(집화 및 배달대리점)을 설정값을 찾지 못한 경우입니다.
 * - 20004 : 집배권역에서 반환된 접소정보가 폐점이나 사용중지 된 점소일 경우 반환되는 값입니다.
 * - 20005 : 배달이나 집화를 처리해 주어야 하는 사원이 설정되지 않은 경우 반환되는 값입니다.
 * - 20006 : 허브터미널에서 분류를 하기 위한 도착지 코드 추출에 실패한 경우 반환되는 값입니다.
 * - 20007 : 서버터미널에서 분류를 하기 위한 분류주소 추출에 실패한 경우 반환되는 값입니다.
 */
@Getter
public enum CjAddressResultType {

    SUCCESS("0", "정제 성공"),
    ILLEGAL_PARAMS("-20000", "CJ 주소 정제 - 파라미터 오류입니다."),
    NOT_FOUND_CUST_ID("-20001", "CJ 주소 정제 - 등록되지 않은 고객입니다."),
    INVALID_ADDRESS("-20002", "CJ 주소 정제 - 주소 정제에 실패했습니다."),
    NOT_FOUND_DELIVERY_AREA("-20003", "CJ 주소 정제 - 집배권역이 없습니다."),
    NOT_FOUND_COLLECTION("-20004", "CJ 주소 정제 - 접소정보가 폐점이나 사용중지 된 점소입니다."),
    NOT_FOUND_EMPLOYEE("-20005", "CJ 주소 정제 - 사원 설정 정보가 없습니다."),
    DESTINATION_EXTRACT_FAIL("-20006", "CJ 주소 정제 - 도착지 코드 추출에 실패했습니다."),
    ADDRESS_DIVIDED_FAIL("-20007", "CJ 주소 정제 - 분류주소 추출에 실패헸습니다."),
    UNABLE_SHIP("-20009", "CJ 주소 정제 - 배송 불가지역입니다.");

    private final String code;
    private final String message;

    CjAddressResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static void checkSuccess(String errorCode) {
        if (!SUCCESS.code.equals(errorCode)) {
            throw new AddressCheckFailException(TmsResultType.ADDRESS_CHECK_FAIL, CjAddressResultType.findBy(errorCode).message);
        }
    }

    public static CjAddressResultType findBy(String errorCode) {
        return Arrays.stream(values())
                .filter(cj -> cj.code.equals(errorCode))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("CJ 에러 코드를 확인할 수 없습니다."));
    }
}

package ai.fassto.tms.domain.common.vo.enums;

import lombok.Getter;

@Getter
public enum TmsResultType {
    SUCCESS("0000", "성공하였습니다."),
    DEFAULT_ERROR("1001", "작업이 실패하였습니다. 담당자에게 문의 부탁드립니다"),
    ACCESS_ERROR("1002", "인증키를 확인해주세요"),
    NO_REGISTERED_INFORMATION("1003", "기등록된 정보가 없습니다."),
    MULTIPLE_INFORMATION_SEARCHED("1004", "복수의 정보가 검색되었습니다."),
    DATA_ALREADY_CANCELED("1005", "이미 취소된 데이터입니다."),
    ILLEGAL_ARGUMENT("1006", "입력 값을 확인해주세요."),
    REQUEST_EXCEEDED("1007", "요청이 초과되었습니다"),
    ILLEGAL_PARCEL_ADDRESS("1008", "유효하지 않은 주소입니다."),
    ILLEGAL_PARCEL_COMPANY("1009", "택배사 정보를 확인할 수 없습니다."),
    ILLEGAL_PARCEL_DATE("1010", "유효한 날짜(시각)이 아닙니다."),
    UNDELIVERED_PARCEL_ADDRESS("1011", "배송불가 지역 입니다."),
    DUPLICATE_INVOICE("1012", "이미 등록된 송장입니다."),
    DUPLICATE_RETURN_INVOICE("1013", "이미 등록된 반품 송장입니다."),
    CUSTOMER_INFO_NOT_FOUND("1014", "고객사 정보가 없습니다."),
    CUSTOMER_WAREHOUSE_NOT_FOUND("1015", "연결된 센터가 없습니다."),
    INVOICE_EXCEEDED("1016","송장번호 대역대가 초과되었습니다."),
    LOGISTICS_INVOICE_NOT_FOUND("1017", "등록된 송장 정보가 없습니다."),
    LOGISTICS_INVOICE_NOT_COLLECT("1018", "송장번호를 채번할 수 없습니다."),
    SOCKET_TIME_OUT("1019", "타임아웃이 발생하였습니다."),
    PARCEL_REGISTER_ERROR("1020", "택배 접수를 실패하였습니다."),
    ADDRESS_CHECK_FAIL("1021", "주소정제에 실패하였습니다.");

    private final String code;
    private final String message;

    TmsResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

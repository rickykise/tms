package ai.fassto.tms.externalservice.parcel.teamfresh.vo.enums;

import lombok.Getter;

@Getter
public enum TeamFreshResultType {
    SUCCESS("0000", "통신성공"),
    SERVER_ERROR("0001", "서버오류"),
    INVALID_IP_OR_APIKEY("0002", "등록되지 않는 IP 또는 apiaccesskey"),
    NO_ORDERS_SEARCHED("0003", "검색된 주문 없음"),
    MULTIPLE_ORDER_SEARCHED("0004", "복수의 주문 검색"),
    ORDER_TYPE_ERROR("0005", "orderType 오류"),
    ORDER_NUMBER_ERROR("0006", "주문번호 오류"),
    REQUIRED_PARAMETER_MISSING("0007", "주문정보의 필수 입력값 누락"),
    SATURDAY_ORDER_NOT_AVAILABLE("0008", "토요일 주문 불가"),
    ORDER_CLOSE("0009", "주문 마감"),
    MAX_ORDER_COUNT_EXCEEDED("0010", "주문 건수 초과"),
    NO_ORDER_INFORMATION("0011", "주문 정보 없음"),
    BARCODE_NUMBER_ERROR("0012", "barcodeNum 오류"),
    BOX_COUNT_ERROR("0013", "박스수량오류"),
    SQL_ERROR("0014", "SQL 오류"),
    REQUEST_TIME_ERROR("0015", "요청시간 오류"),
    JSON_TYPE_ERROR("0016", "JSON TYPE 오류"),
    SERVICE_NOT_USE("0017", "풀필먼트 서비스 미사용 고객사"),
    ALREADY_CANCELED_DATA("0018", "이미 취소된 데이터"),
    CAN_ORDER_WITHIN_7_DAYS("0019", "주문 최대 7일 이내 가능"),
    ORDER_DATE_ERROR("0020", "orderDate 오류");

    private final String teamFreshCode;
    private final String desc;

    TeamFreshResultType(String teamFreshCode, String desc) {
        this.teamFreshCode = teamFreshCode;
        this.desc = desc;
    }

    public static boolean isSuccess(String teamFreshCode) {
        return SUCCESS.teamFreshCode.equals(teamFreshCode);
    }
}

package ai.fassto.tms.externalservice.parcel.teamfresh.vo.enums;

import lombok.Getter;

@Getter
public enum ParcelResultType {
    SUCCESS("1000", "저장"),
    FAIL("1001", "저장 실패"),
    UNDELIVERED_AREA("1002", "배송불가 권역"),
    PARAMETER_ERROR("1003", "파라미터 오류"),
    ADDRESS_ERROR("1004", "주소 오류"),
    REQUIRED_VALUE_NOT_FOUND("1010", "필수값 누락"),
    PRODUCT_QUANTITY_ERROR("1011", "상품수량 오류"),
    MULTIPLE_ADDRESSES_SEARCHED("1012", "다수주소 검색됨"),
    ORDER_TYPE_ERROR("1013", "orderType 값 오류"),
    ORDER_NOT_FOUND("1014", "주문 정보 없음");

    private final String teamFreshCode;
    private final String desc;

    ParcelResultType(String teamFreshCode, String desc) {
        this.teamFreshCode = teamFreshCode;
        this.desc = desc;
    }

    public static boolean isSuccess(String teamFreshCode) {
        return SUCCESS.teamFreshCode.equals(teamFreshCode);
    }
}

package ai.fassto.tms.dataaccess.parcel.lotte.vo;

public class LotteParcelConst {
    // 주소정제
    public static final String SUPER_CUST_CODE = "183957";      // 대표거래처 코드
    public static final String LOTTE_ADDR_NETWORK_CODE = "00";   // 롯데 주소정제 서비스 구분 코드 (00: 일반)
    public static final String LOTTE_ADDR_SUCCESS = "success";   // 주소정제 처리 결과 SUCCESS값

    // 택배신청
    public static final String LOTTE_INVOICE_FIXED_ACPTSSEQ = "1";     // 전송차수 (1고정)
    public static final String LOTTE_INVOICE_FIXED_QTY = "1";    // 수량 1 고정값
    public static final String LOTTE_INVOICE_FIXED_ORGINAL_INV_NO = "999999999999";    // 원송장번호 고정값
    public static final String LOTTE_INVOICE_FIXED_ITEM_SIZE = "1";    // 박스크기(1:소, 2:중, 3:대) 1 고정
    public static final String LOTTE_INVOICE_FIXED_ITEM_NAME = "상품";  // 상품명 고정값
    public static final String LOTTE_INVOICE_FIXED_CON_FLG = "001";    // 결제구분 (001: 신용, 003: 착불)
    public static final String LOTTE_INVOICE_FIXED_SUM_FARE = "0";     // 운임
    public static final String LOTTE_INVOICE_FIXED_TRS_STAT_CD = "1";   // 고정값

    // return
    public static final String LOTTE_PARCEL_CODE = "LOTTE";

    private LotteParcelConst() {
    }
}

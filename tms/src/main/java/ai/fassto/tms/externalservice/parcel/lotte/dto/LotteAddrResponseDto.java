package ai.fassto.tms.externalservice.parcel.lotte.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record LotteAddrResponseDto(
        // 기본 정보
        String result,      // 처리결과 (success / error)
        String message,     // 오류메세지

        // 주소데이터
        @JsonProperty("city_gun_gu")
        String cityGunGu,   // 시군구
        String dong,        // 읍면동
        @JsonProperty("area_no")
        String areaNo,      // 정제된 5자리 우편번호
        @JsonProperty("zip_no")
        String zipNo,       // 정제된 6자리 우편번호

        // 비용
        @JsonProperty("ship_fare")
        int shipFare,    // 도선료
        @JsonProperty("air_fare")
        int airFare,     // 항공료

        // 배송정보
        @JsonProperty("tml_cd")
        String tmlCd,       // 노선구분
        @JsonProperty("tml_nm")
        String tmlNm,       // 분류코드
        @JsonProperty("filt_cd")
        String filtCd,      // 도착지 코드
        @JsonProperty("brnshp_cd")
        String brnshpCd,    // 주소지 집배달 대리점코드
        @JsonProperty("brnshp_nm")
        String brnshpNm,    // 주소지 집배달 대리점명
        @JsonProperty("bld_annm")
        String bldAnnm,     // 주소지 건물별칭
        @JsonProperty("emp_nm")
        String empNm,       // 주소지 집배송사원명
        String lgtd,        // 주소지 경도
        String lttd,        // 주소지 위도
        @JsonProperty("dlv_msg")
        String dlvMsg      // 배달가능여부메세지 ( 값이 있을때는 배달 불가 )
) {
    private static final String LOTTE_ADDR_SUCCESS = "success";

    public boolean isSuccess() {
        return LOTTE_ADDR_SUCCESS.equals(this.result);
    }
}

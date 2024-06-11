package ai.fassto.tms.externalservice.parcel.hanjin.dto;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.core.exception.AddressCheckFailException;
import ai.fassto.tms.externalservice.parcel.hanjin.vo.enums.HanjinAddressType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record HanjinAddrResponseDto(
        @JsonProperty("result_code")
        String resultCode,          // 결과코드
        @JsonProperty("result_message")
        String resultMessage,       // 결과메세지
        @JsonProperty("msg_key")
        String msgKey,              // 메세지 key
        @JsonProperty("s_tml_nam")
        String sTmlNam,             // 집하지 터미널명
        @JsonProperty("s_tml_cod")
        String sTmlCod,             // 집하지 터미널코드
        @JsonProperty("zip_code")
        String zipCod,              // 정제된 우편번호
        @JsonProperty("tml_nam")
        String tmlNam,              // 도착지 터미널명
        @JsonProperty("tml_cod")
        String tmlCod,              // 도착지 터미널코드
        @JsonProperty("cen_nam")
        String cenNam,              // 도착지 집배점명
        @JsonProperty("cen_cod")
        String cenCod,              // 도착지 집배점코드
        @JsonProperty("pd_tim")
        String pdTim,               // 집배소요시간
        @JsonProperty("dom_rgn")
        String domRgn,
        // 권역구분(1:수도권, 2:강원권역, 3:충청권역, 4:경남권역, 5:경북권역, 6:호남권역, 7:제주권역 (항공료발생), 9:도서지역 (도선료발생))
        @JsonProperty("hub_cod")
        String hubCod,              // 허브코드(대분류코드)
        @JsonProperty("dom_mid")
        String domMid,              // 중분류코드
        @JsonProperty("grp_rnk")
        String grpRnk,              // 소분류코드(배송사원)
        @JsonProperty("es_nam")
        String esNam,               // 배송사원명
        @JsonProperty("es_cod")
        String esCod,               // 배송사원분류코드
        @JsonProperty("prt_add")
        String prtAdd               // 주소 출력정보
) {
    public boolean isStopTrading() {
        return HanjinAddressType.isStopTrading(resultCode);
    }

    public void checkSuccess() {
        if (!HanjinAddressType.isSuccess(resultCode)) {
            throw new AddressCheckFailException(TmsResultType.ADDRESS_CHECK_FAIL, resultMessage);
        }
    }
}

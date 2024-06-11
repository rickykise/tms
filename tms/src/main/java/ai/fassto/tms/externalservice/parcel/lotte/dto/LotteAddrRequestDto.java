package ai.fassto.tms.externalservice.parcel.lotte.dto;

import lombok.Builder;

@Builder
public record LotteAddrRequestDto(
        String id,              // 거래처코드(6자리) CUST_ID
        String network,         // 서비스구분 (00: 일반, 01:의류, 04:소형)
        String address,         // 주소
        String pick_area_no,    // 출발지 우편번호
        String pick_address     // 출발지 주소
) {
    private static final String LOTTE_ADDR_NETWORK_CODE = "00";

    public LotteAddrRequestDto {
        network = LOTTE_ADDR_NETWORK_CODE;
    }
}

package ai.fassto.tms.domain.parcel.application.dto.finalcall.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record ParcelFinalCallResponseDto(
        @Schema(description = "센터 코드", example = "YI21")
        String warehouseCode,           // 센터코드
        @Schema(description = "고객사 코드", example = "04365")
        String customerCode,            // 고객사코드
        @Schema(description = "고객사 명", example = "빙그레")
        String customerName,            // 고객사명
        @Schema(description = "배송사 코드", example = "Hanjin")
        String deliveryCompanyCode,     // 배송사코드
        @Schema(description = "배송 타입", example = "Domestic-24H Delivery")
        String deliveryType,            // 배송구분
        @Schema(description = "특화센터납 구분 여부", example = "Y/N")
        String changingShippingOption,  // 특화센터납 구분 여부
        @Schema(description = "파스토 주문번호", example = "1547_23013100001")
        String orderNo,                 // 주문번호
        @Schema(description = "파스토 출고요청번호", example = "1TESTOO230201000106")
        String slipNo,                  // 출고 요청 번호
        @Schema(description = "상품출고일(집하요청일자)", example = "20230203")
        String pickDate,                // 상품출고일(집하요청일자)
        @Schema(description = "보내는사람 이름", example = "고우석")
        String senderName,              // 보내는사람
        @Schema(description = "보내는사람 전화번호", example = "01011112222")
        String senderPhone,             // 보내는사람 전화번호
        @Schema(description = "보내는사람 전화번호2", example = "01011112222")
        String senderPhoneEtc,
        @Schema(description = "보내는 지역 우편번호", example = "17180")
        String senderZipCode,           // 보내는사람 우편번호
        @Schema(description = "보내는사람 기본주소", example = "경기 용인시 처인구 백암면 원설로 691 (고안리)")
        String senderAddress,           // 보내는사람 주소
        @Schema(description = "보내는사람 상세주소", example = "102동 1123호")
        String senderAddressDetail,     // 보내는사람 상세주소
        @NotBlank
        @Schema(description = "받는사람 이름", example = "김광현")
        String receiverName,            // 받는사람
        @Schema(description = "받는사람 전화번호", example = "010-1111-2222 (하이픈으로 각 자리 구분)")
        String receiverPhone,          // 받는사람 전화번호 ( "-" 로 각 자리가 구분되어있음 )
        @Schema(description = "받는사람 전화번호2", example = "010-1111-2222 (하이픈으로 각 자리 구분)")
        String receiverPhoneEtc,
        @Schema(description = "받는지역 우편번호", example = "10887")
        String receiverZipCode,         // 받는사람 우편번호
        @Schema(description = "받는사람 기본주소", example = "서울특별시 송파구 올림픽로 25")
        String receiverAddress,         // 받는사람 주소
        @Schema(description = "받는사람 상세주소", example = "1층 문앞")
        String receiverAddressDetail,   // 받는사람 상세주소
        @Schema(description = "배송 요청 사항", example = "문 앞에 놔주세요")
        String shipRequestMemo,         // 배송요청사항
        @Schema(description = "공동현관 비밀번호", example = "4477")
        String entrancePassword,        // 공동현관 비밀번호
        @Schema
        List<PackageDto> packages
) {
}
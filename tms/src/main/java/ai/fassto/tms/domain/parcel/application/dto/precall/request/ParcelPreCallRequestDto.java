package ai.fassto.tms.domain.parcel.application.dto.precall.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ParcelPreCallRequestDto(
        @NotBlank
        @Schema(description = "센터 코드", example = "TEST", requiredMode = Schema.RequiredMode.REQUIRED)
        String warehouseCode,           // 센터코드
        @NotBlank
        @Schema(description = "고객사 코드", example = "07970", requiredMode = Schema.RequiredMode.REQUIRED)
        String customerCode,            // 고객사코드
        @Schema(description = "고객사 명", example = "테스트고객사")
        String customerName,            // 고객사명
        @NotBlank
        @Schema(description = "배송사 코드", example = "Hanjin", requiredMode = Schema.RequiredMode.REQUIRED)
        String deliveryCompanyCode,     // 배송사코드
        @Schema(description = "배송 타입", example = "Domestic-24H Delivery")
        String deliveryType,            // 배송구분
        @NotBlank
        @Schema(description = "특화센터납 구분 여부", example = "Y", requiredMode = Schema.RequiredMode.REQUIRED)
        String changingShippingOption,  // 특화센터납 구분 여부
        @NotBlank
        @Schema(description = "파스토 주문번호", example = "1547_23013100001", requiredMode = Schema.RequiredMode.REQUIRED)
        String orderNo,                 // 주문번호
        @NotBlank
        @Schema(description = "파스토 출고요청번호", example = "1TESTOO230201000106", requiredMode = Schema.RequiredMode.REQUIRED)
        String slipNo,                  // 출고 요청 번호
        @Schema(description = "상품출고일(집하요청일자)", example = "20230523", requiredMode = Schema.RequiredMode.REQUIRED)
        String pickDate,                // 상품출고일(집하요청일자)
        @NotBlank
        @Schema(description = "보내는사람 이름", example = "고우석", requiredMode = Schema.RequiredMode.REQUIRED)
        String senderName,              // 보내는사람
        @NotBlank
        @Schema(description = "보내는사람 전화번호", example = "01011112222", requiredMode = Schema.RequiredMode.REQUIRED)
        String senderPhone,             // 보내는사람 전화번호
        @Schema(description = "보내는사람 전화번호2", example = "01022221111")
        String senderPhoneEtc,
        @NotBlank
        @Schema(description = "보내는 지역 우편번호", example = "17180", requiredMode = Schema.RequiredMode.REQUIRED)
        String senderZipCode,           // 보내는사람 우편번호
        @NotBlank
        @Schema(description = "보내는사람 기본주소", example = "경기 용인시 처인구 백암면 원설로 691 (고안리)", requiredMode = Schema.RequiredMode.REQUIRED)
        String senderAddress,           // 보내는사람 주소
        @Schema(description = "보내는사람 상세주소", example = "102동 1123호")
        String senderAddressDetail,     // 보내는사람 상세주소
        @NotBlank
        @Schema(description = "받는사람 이름", example = "김광현", requiredMode = Schema.RequiredMode.REQUIRED)
        String receiverName,            // 받는사람
        @NotBlank
        @Schema(description = "받는사람 전화번호", example = "010-3333-4444", requiredMode = Schema.RequiredMode.REQUIRED)
        String receiverPhone,          // 받는사람 전화번호 ( "-" 로 각 자리가 구분되어있음 )
        @Schema(description = "받는사람 전화번호2", example = "010-4444-5555")
        String receiverPhoneEtc,
        @Schema(description = "받는지역 우편번호", example = "05500")
        String receiverZipCode,         // 받는사람 우편번호
        @NotBlank
        @Schema(description = "받는사람 기본주소", example = "서울특별시 송파구 올림픽로 25", requiredMode = Schema.RequiredMode.REQUIRED)
        String receiverAddress,         // 받는사람 주소
        @Schema(description = "받는사람 상세주소", example = "1층 문앞", requiredMode = Schema.RequiredMode.REQUIRED)
        String receiverAddressDetail,   // 받는사람 상세주소
        @Schema(description = "배송 요청 사항", example = "문 앞에 놔주세요")
        String shipRequestMemo,         // 배송요청사항
        @Schema(description = "공동현관 비밀번호", example = "4477")
        String entrancePassword,        // 공동현관 비밀번호
        @Positive
        @Schema(description = "배송 박스 갯수", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        int boxQty,                     // 박스수량
        @Schema(description = "PRE-CALL-BoxDto")
        List<PreCallBoxDto> boxes
) {
}

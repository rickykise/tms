package ai.fassto.tms.domain.parcel.application.dto.finalcall.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;


@Schema(description = "FINAL-CALL-BoxDto")
public record FinalCallBoxDto(
        @NotBlank
        @Schema(description = "배송박스 식별 ID", example = "id1284179234712", requiredMode = Schema.RequiredMode.REQUIRED)
        String boxID,                 // 박스 식별자
        @Schema(description = "송장 번호", example = "")
        String invoiceNo,                   // 송장 번호
        @Positive
        @Schema(description = "상품 갯수", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        int productQty,               // 상품 갯수
        @NotBlank
        @Schema(description = "배송 박스 크기 타입", example = "L", requiredMode = Schema.RequiredMode.REQUIRED)
        String type,
        @NotBlank
        @Schema(description = "배송 박스 타입", example = "Carton", requiredMode = Schema.RequiredMode.REQUIRED)
        String category,
        @PositiveOrZero
        @Schema(description = "박스 너비", example = "20.0", requiredMode = Schema.RequiredMode.REQUIRED)
        float boxWidth,
        @PositiveOrZero
        @Schema(description = "박스 높이", example = "13.0", requiredMode = Schema.RequiredMode.REQUIRED)
        float boxHeight,
        @PositiveOrZero
        @Schema(description = "박스 깊이", example = "10.0", requiredMode = Schema.RequiredMode.REQUIRED)
        float boxDepth,
        @Schema(description = "박스 무개", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
        int boxWeight,
        @Valid
        @NotNull
        @Schema(description = "FINAL-CALL-ITEM")
        List<FinalCallItemDto> items
) {
}

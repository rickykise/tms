package ai.fassto.tms.domain.parcel.application.dto.finalcall.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Schema(description = "FINAL-CALL-ITEM")
public record FinalCallItemDto(
        @NotBlank
        @Schema(description = "상품 코드", example = "07970ELS00002", requiredMode = Schema.RequiredMode.REQUIRED)
        String code,          // 상품 코드
        @Positive
        @Schema(description = "상품 수량", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        int qty              // 상품 수량
) {
}

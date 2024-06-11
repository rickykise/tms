package ai.fassto.tms.domain.parcel.application.dto.precall.request;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "PRE-CALL-ITEM")
public record PreCallItemDto(
        @Schema(description = "상품 코드", example = "07970ELS00002")
        String code,          // 상품 코드
        @Schema(description = "상품 수량", example = "2")
        int qty               // 상품 수량
) {
}
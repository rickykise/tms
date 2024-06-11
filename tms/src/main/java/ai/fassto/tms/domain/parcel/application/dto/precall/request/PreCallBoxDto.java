package ai.fassto.tms.domain.parcel.application.dto.precall.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "PRE-CALL-BoxDto")
public record PreCallBoxDto(
        @Schema(description = "상품 갯수", example = "2")
        int productQty,              // 상품 갯수
        @Schema(description = "송장 번호", example = "331245653121")
        String invoiceNo,
        @Schema(description = "PRE-CALL-ITEM")
        List<PreCallItemDto> items
) {
}

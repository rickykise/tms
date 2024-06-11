package ai.fassto.tms.domain.parcel.application.dto.finalcall.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record PackageDto(
        @Schema(description = "배송박스 식별 ID", example = "id1284179234712")
        String boxID,
        @Schema(description = "송장 번호", example = "111122223333")
        String invoiceNumber,
        @Schema(description = "계약운임", example = "1000")
        int basicFare,
        @Schema(description = "제주운임", example = "1000")
        int dealFare,
        @Schema(description = "항공운임", example = "1000")
        int airFare,
        @Schema(description = "도선운임", example = "1000")
        int shipFare,
        @Schema(description = "배송 박스 타입", example = "PAPER_BOX / STYROFOAM_BOX")
        String boxType,
        @Schema(description = "박스 너비", example = "20.0")
        float boxWidth,
        @Schema(description = "박스 높이", example = "13.0")
        float boxHeight,
        @Schema(description = "박스 깊이", example = "10.0")
        float boxDepth,
        @Schema(description = "박스 무개", example = "10")
        int boxWeight,
        @Schema(description = "박스 무개", example = "10")
        PrintedDocumentDto printDocument
) {
}

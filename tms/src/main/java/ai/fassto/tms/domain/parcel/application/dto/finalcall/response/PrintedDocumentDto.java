package ai.fassto.tms.domain.parcel.application.dto.finalcall.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PrintedDocumentDto(
        @Schema(description = "송장 정보", example = "협의 필요")
        String documentName,         // 송장 사이즈 등등
        @Schema(description = "인코딩된 송장 zpl 문자열", example = "XlhBCl5DSTEzCl5QUTEKXlBPTgpe...")
        String encodedContents       // 인코딩된 송장 zpl
) {
}

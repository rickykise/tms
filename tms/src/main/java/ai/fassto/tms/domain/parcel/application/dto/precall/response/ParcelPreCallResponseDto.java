package ai.fassto.tms.domain.parcel.application.dto.precall.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ParcelPreCallResponseDto(
        @Schema(description = "FMS 출고요청번호", example = "YI21124412341")
        String slipNo,                  // fms 의 slipNo ( 출고 요청 번호 )
        @Schema(description = "송장번호", example = "111122223333")
        String invoiceNo            // 송장
) {
}


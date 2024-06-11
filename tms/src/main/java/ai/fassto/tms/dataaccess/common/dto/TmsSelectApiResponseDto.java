package ai.fassto.tms.dataaccess.common.dto;

import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiType;
import lombok.Builder;

@Builder
public record TmsSelectApiResponseDto(
        String slipNo,
        TmsApiType apiType
) {
}

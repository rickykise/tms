package ai.fassto.tms.dataaccess.common.dto;

import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiStatusType;

public record TmsApiResponseDto(
        String response,
        TmsApiStatusType status
) {
}

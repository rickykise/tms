package ai.fassto.tms.dataaccess.common.dto;

import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiStatusType;
import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiType;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public record TmsUpdateApiResponseDto(
        BigInteger seq,
        TmsApiStatusType status,
        String response,
        String slipNo,
        TmsApiType apiType
) {
}

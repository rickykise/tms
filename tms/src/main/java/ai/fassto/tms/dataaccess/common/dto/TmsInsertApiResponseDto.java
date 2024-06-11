package ai.fassto.tms.dataaccess.common.dto;

import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiStatusType;
import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiType;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public record TmsInsertApiResponseDto(
        BigInteger seq,
        String slipNo,
        TmsApiType apiType,
        TmsApiStatusType status
) {
}

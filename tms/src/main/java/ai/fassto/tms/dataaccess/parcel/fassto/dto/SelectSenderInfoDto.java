package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record SelectSenderInfoDto(
        String slipNo,
        String warehouseCode,
        String customerCode,
        String orderNo
) {
}

package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record SelectEcremmoceParcelDataDto(
        String warehouseCode,
        String customerCode,
        String orderNo
) {
}

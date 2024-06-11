package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record SelectLotteBrnclctDto(
        String deliveryCompanyCode,
        String warehouseCode
) {
}

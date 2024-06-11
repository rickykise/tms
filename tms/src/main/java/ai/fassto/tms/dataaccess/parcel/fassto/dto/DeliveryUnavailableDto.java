package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import lombok.Builder;

@Builder
public record DeliveryUnavailableDto(
        String warehouseCode,
        String zipCode,
        ParcelCompanyType parcelCompanyType
) {
}

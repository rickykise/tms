package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import lombok.Builder;

@Builder
public record SelectCenterContractCodeDto(
        String whCd,
        ParcelCompanyType parcelCompanyType,
        String customerCode
) {
}

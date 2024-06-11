package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.core.exception.ParcelCompanyNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CompanySpecificHelperFactory {
    private final Map<ParcelCompanyType, CompanySpecificHelper> helperMap = new HashMap<>();

    public CompanySpecificHelperFactory(List<CompanySpecificHelper> helpers) {
        helpers.forEach(helper -> this.helperMap.put(helper.parcelCompanyType(), helper));
    }

    public CompanySpecificHelper getHelperBy(ParcelCompanyType parcelCompanyType) {
        return Optional.ofNullable(helperMap.get(parcelCompanyType))
                .orElseThrow(() -> new ParcelCompanyNotFoundException(TmsResultType.ILLEGAL_PARCEL_COMPANY, "등록되지 않은 배송사 코드입니다."));
    }
}

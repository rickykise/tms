package ai.fassto.tms.domain.parcel.application.vo;

import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.application.vo.enums.SoloChainShippingServiceType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeliveryInfo {
    private final ParcelCompanyType parcelCompanyType;
    private final SoloChainShippingServiceType deliveryType;
    private final String changingShippingOption;
    private final LocalDate pickDate;
    private String contractCode;

    @Builder
    public DeliveryInfo(ParcelCompanyType parcelCompanyType, SoloChainShippingServiceType deliveryType, String changingShippingOption, LocalDate pickDate, String contractCode) {
        this.parcelCompanyType = parcelCompanyType;
        this.deliveryType = deliveryType;
        this.changingShippingOption = changingShippingOption;
        this.pickDate = pickDate;
        this.contractCode = contractCode;
    }

    public void updateContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public boolean isHanjinGuaranteeOrder() {
        return this.deliveryType == SoloChainShippingServiceType.HANJIN_GUARANTEE;
    }
}

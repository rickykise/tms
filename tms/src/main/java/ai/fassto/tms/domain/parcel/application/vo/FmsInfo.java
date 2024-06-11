package ai.fassto.tms.domain.parcel.application.vo;

import lombok.Builder;

@Builder
public record FmsInfo(
        String slipNo,
        String warehouseCode,
        String customerCode,
        String customerName,
        String orderNo
) {

    public String chainLogisOrderIdFromCorp() {
        return warehouseCode + customerCode + orderNo;
    }
}

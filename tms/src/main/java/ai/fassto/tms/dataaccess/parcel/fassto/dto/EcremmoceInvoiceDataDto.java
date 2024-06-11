package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record EcremmoceInvoiceDataDto(
        String invcNo,
        String ordNo,
        String rcptYmd,
        String frtDvCd,
        String boxTypeCd,
        int boxQty,
        String sendrNm,
        String sendrTelNo,
        String rcvrNm,
        String rcvrTelNo,
        String rcvrAddr,
        String markGdsNm,
        String gdsNm,
        int gdsQty,
        String platform,
        String logiCenter
) {
}

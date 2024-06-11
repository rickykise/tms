package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record CjInvoiceDataDto(
        String invcNo,
        String frtDvCd,
        String boxTypeCd,
        int boxQty,
        String sendrNm,
        String sendrTelNo1,
        String sendrTelNo2,
        String sendrTelNo3,
        String sendrAddr,
        String sendrDetailAddr,
        String rcvrNm,
        String rcvrTelNo1,
        String rcvrTelNo2,
        String rcvrTelNo3,
        String rcvrTelEtcNo1,
        String rcvrTelEtcNo2,
        String rcvrTelEtcNo3,
        String rcvrAddr,
        String rcvrDetailAddr,
        String markGdsNm,
        String gdsNm,
        int gdsQty,
        String endNo,
        String subEndNo,
        String manBranNm,
        String cldvEmpNm,
        String cldvEmpClsCd,
        String shortAddr
) {
}

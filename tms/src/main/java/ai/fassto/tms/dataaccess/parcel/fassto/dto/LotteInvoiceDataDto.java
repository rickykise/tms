package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record LotteInvoiceDataDto(
        String invcNo,
        String conFlg,
        String sendrNm,
        String sendrTelNo1,
        String sendrTelNo2,
        String sendrTelNo3,
        String sendrAddr,
        String rcvrNm,
        String rcvrTelNo1,
        String rcvrTelNo2,
        String rcvrTelNo3,
        String rcvrTelEtcNo1,
        String rcvrTelEtcNo2,
        String rcvrTelEtcNo3,
        String rcvrAddr,
        String markGdsNm,
        String gdsNm,
        int gdsQty,
        int sumFare,
        String tmlCd,
        String tmlNm,
        String cityGunGu,
        String eupMuinDong,
        String filtCd,
        String brnclctCd,
        String brnclctNm,
        String brnclctTelNo,
        String brnshpNm,
        String dlvMsg,
        String empNm
) {
}

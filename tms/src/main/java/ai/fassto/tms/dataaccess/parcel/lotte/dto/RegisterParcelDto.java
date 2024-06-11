package ai.fassto.tms.dataaccess.parcel.lotte.dto;

import lombok.Builder;

@Builder
public record RegisterParcelDto(
        String acptSseq,
        String wkSct,
        String ordNo,
        String invNo,
        String superCustCd,
        String custCd,
        String itemSize,
        String itemNm,
        String qty,
        String dlvMsg,
        String acperNm,
        String acperTel,
        String acperHtel,
        String acperZipNo,
        String acperAddr,
        String snperNm,
        String snperTel,
        String snperHtel,
        String snperZipNo,
        String snperAddr,
        String conFlg,
        String sumFare,
        String orgInvNo,
        String trsStatCd
) {
}

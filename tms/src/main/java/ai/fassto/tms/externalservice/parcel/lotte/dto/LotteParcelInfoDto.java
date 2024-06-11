package ai.fassto.tms.externalservice.parcel.lotte.dto;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.LotteBrnclctDto;
import ai.fassto.tms.domain.parcel.application.vo.invoice.LotteInvoiceData;
import lombok.Builder;

@Builder
public record LotteParcelInfoDto(
        String conFlg,
        String tmlCd,
        String tmlNm,
        String cityGunGu,
        String eupMuinDong,
        String filtCd,
        String brnshpNm,
        String empNm,
        int shipFare,
        int airFare
) {
    private static final String LOTTE_INVOICE_FIXED_CON_FLG = "001";    // 결제구분 (001: 신용, 003: 착불)

    public LotteParcelInfoDto {
        conFlg = LOTTE_INVOICE_FIXED_CON_FLG;
    }

    public LotteInvoiceData toLotteInvoiceData(LotteBrnclctDto lotteBrnclctDto) {
        return LotteInvoiceData.builder()
                .conFlg(conFlg)
                .tmlCd(tmlCd)
                .tmlNm(tmlNm)
                .cityGunGu(cityGunGu)
                .eupMuinDong(eupMuinDong)
                .filtCd(filtCd)
                .brnshpNm(brnshpNm)
                .brnclctCd(lotteBrnclctDto.brnclctCd())
                .brnclctNm(lotteBrnclctDto.brnclctNm())
                .brnclctTelNo(lotteBrnclctDto.brnclctTelNo())
                .empNm(empNm)
                .build();
    }
}

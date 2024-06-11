package ai.fassto.tms.domain.parcel.application.vo;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Memo {
    // 배송요청사항
    private final String shipRequestMemo;
    // 공동현관 비밀번호
    private final String entrancePassword;

    @Builder
    public Memo(String shipRequestMemo, String entrancePassword) {
        this.shipRequestMemo = shipRequestMemo;
        this.entrancePassword = entrancePassword;
    }
}

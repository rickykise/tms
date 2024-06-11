package ai.fassto.tms.externalservice.parcel.cj.dto;

import ai.fassto.tms.externalservice.parcel.cj.vo.CjAddressResultType;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.commons.lang3.StringUtils;

public record CjAddrResponseDto(
        /**
         * Address Info
         */
        String clntNum,                        // CJ대한통운 고객 ID
        String clntMgmCustCd,                // CJ대한통운 고객관리거래처코드
        String prngDivCd,                    // 예약구분코드 (01: 일반, 02: 반품)
        String fareDiv,                        // 운임구분코드 (01: 선불, 02: 착불, 03: 신용)
        String cntrLarcCd,                    // 계약상품코드
        String boxTyp,                        // 박스타입코드 (01: 극소, 02: 소, 03: 중, 04: 대, 05: 특대)
        String sndprsnAddr,                    // 보내는분 주소
        String rcvrAddr,                    // 받는분 주소

        /**
         * Sender
         */
        String sndprZipNum,                    // [주소정제] 송화인 우편번호
        String sndprOldAddr,                // [주소정제] 송화인 지번주소
        String sndprOldAddrDtl,                // [주소정제] 송화인 지번주소 상세
        String sndprNewAddr,                // [주소정제] 송화인 도로명주소
        String sndprNewAddrDtl,                // [주소정제] 송화인 도로명주소 상세

        String sndprShortAddr,                // [집배권역] 송화인 주소약칭
        String sndprEtcAddr,                // [주소정제] 송화인 기타주소 (도로명 주소일 경우 연계되는 지번주소 반환)
        String sndprClsfAddr,                // [주소정제] 분류를 용이하게 하기 위한 주소약칭
        String sndprNewAddrYn,                // [주소정제] 송화인 신구주소여부

        /**
         * Delivery Area
         */
        String gthPreArrBranCd,                // [집배권역] 집화예정점소코드
        String gthPreArrBranNm,                // [집배권역] 집화예정점소명
        String gthPreArrBranShortNm,        // [집배권역] 집화예정점소약칭
        String gthPreArrEmpNum,                // [집배권역] 집화예정사원번호
        String gthPreArrEmpNm,                // [집배권역] 집화예정사원명
        String gthPreArrEmpNickNm,            // [집배권역] 배달예정사원명
        String gthClsfCd,                    // [집배권역] 집화터미널코드
        String gthSubClsfCd,                // [집배권역] 집화터미널 소분류코드
        String gthClsfNm,                    // [집배권역] 집화터미널명

        /**
         * Receiver
         */
        String rcvrZipNum,                    // [주소정제] 수화인 우편번호
        String rcvrOldAddr,                    // [주소정제] 수화인 지번주소
        String rcvrOldAddrDtl,                // [주소정제] 수화인 지번주소 상세
        String rcvrNewAddr,                    // [주소정제] 수화인 도로명주소
        String rcvrNewAddrDtl,                // [주소정제] 수화인 도로명주소 상세

        String rcvrShortAddr,                // [집배권역] 수화인 주소약칭
        String rcvrEtcAddr,                    // [주소정제] 수화인 기타주소(도로명 주소일 경우 연계되는 지번주소 반환)
        String rcvrClsfAddr,                // [주소정제] 분류를 용이하게 하기 위한 주소약칭
        String rcvrNewAddrYn,                // [주소정제] 수화인 신구주소여부

        /**
         * Delivery Area
         */
        String dlvPreArrBranCd,                // [집배권역] 배달예정점소코드
        String dlvPreArrBranNm,                // [집배권역] 배달예정점소명
        String dlvPreArrBranShortNm,        // [집배권역] 배달예정점소약칭
        String dlvPreArrEmpNum,                // [집배권역] 배달예정사원번호
        String dlvPreArrEmpNm,                // [집배권역] 배달예정사원명
        String dlvPreArrEmpNickNm,            // [집배권역] 배달예정사원분류코드
        String dlvClsfCd,                    // [집배권역] 배달터미널코드 (도착지코드)
        String dlvSubClsfCd,                // [집배권역] 배달터미널 소분류코드
        String dlvClsfNm,                    // [집배권역] 배달터미널명

        /**
         * Contract Fare
         */
        int bscFare,                        // [계약운임] 계약운임
        int jejuFare,                    // [계약운임] 제주운임
        int dealFare,                    // [계약운임] 취급운임
        int ferryFare,                    // [계약운임] 도선운임

        /**
         * API Result
         */
        @JsonAlias("errorCd")
        String errorCode,                   // [정제결과] 오류코드
        @JsonAlias("errorMsg")
        String errorMessage                // [정제결과] 오류메세지
) {
    public void checkSuccess() {
        CjAddressResultType.checkSuccess(this.errorCode);
    }

    public String senderZipCode() {
        return this.sndprZipNum;
    }

    public String checkedSenderAddress() {
        if (this.isNewAddress(this.sndprNewAddr)) {
            return this.sndprNewAddr;
        }
        return this.sndprOldAddr;
    }

    public String checkedSenderAddressDetail() {
        if (this.isNewAddress(this.sndprNewAddr)) {
            return this.sndprNewAddrDtl;
        }
        return this.sndprOldAddrDtl;
    }

    public String receiverZipCode() {
        return this.rcvrZipNum;
    }

    public String checkedReceiverAddress() {
        if (this.isNewAddress(this.rcvrNewAddr)) {
            return this.rcvrNewAddr;
        }
        return this.rcvrOldAddr;
    }

    public String checkedReceiverAddressDetail() {
        if (this.isNewAddress(this.rcvrNewAddr)) {
            return this.rcvrNewAddrDtl;
        }
        return this.rcvrOldAddrDtl;
    }

    private boolean isNewAddress(String target) {
        return StringUtils.isNotEmpty(target);
    }
}

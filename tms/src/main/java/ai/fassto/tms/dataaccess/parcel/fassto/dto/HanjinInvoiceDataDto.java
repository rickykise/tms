package ai.fassto.tms.dataaccess.parcel.fassto.dto;

import lombok.Builder;

@Builder
public record HanjinInvoiceDataDto(
        String invcNo,             // 운송장번호
        String boxTyp,             // 박스 타입
        int boxQty,
        String customerName,       // 고객사 명
        String sendrTelNo1,        // 송화인 전화번호 1
        String sendrTelNo2,        // 송화인 전화번호 2
        String sendrTelNo3,        // 송화인 전화번호 3
        String sendrAddr,          // 송화인 주소
        String sendrDetailAddr,    // 송화인 상세주소
        String rcvrNm,             // 수화인명
        String rcvrTelNo1,         // 수화인 전화번호 1
        String rcvrTelNo2,         // 수화인 전화번호 2
        String rcvrTelNo3,         // 수화인 전화번호 3
        String rcvrTelEtcNo1,      // 수화인 전화번호 기타 1
        String rcvrTelEtcNo2,      // 수화인 전화번호 기타 2
        String rcvrTelEtcNo3,      // 수화인 전화번호 기타 3
        String rcvrAddr,           // 수화인 주소
        String rcvrDetailAddr,     // 수화인 상세 주소
        String markGdsNm,          // 표시상품
        String gdsNm,              // 상품명
        int gdsQty,                // 상품 수량
        String hubCod,             // 허브 코드
        String rcvTmlNam,          // 착지 터미널명
        String rcvTmlCod,          // 도착지 터미널 코드
        String cenCod,             // 도착지 영업소 코드
        String cenNam,             // 도착지 영업소명
        String domMid,             // 중분류 코드
        String domPdz,             // 소분류 코드
        String esNam,              // 배송원 명
        String sendTmlCod,         // 발송지 터미널 코드
        String sendTmlNam,         // 발송지 터미널 명
        String pdzNam,             // 도착지 집배구역명
        String payCon,             // 운임 지급 기준
        String domRgn,             // 제주/도서 구분
        String customerMemo       // 고객 배송 메모
) {
}

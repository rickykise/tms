package ai.fassto.tms.externalservice.parcel.cj.dto;

import ai.fassto.tms.domain.parcel.application.vo.Address;
import ai.fassto.tms.domain.parcel.application.vo.Fare;
import ai.fassto.tms.domain.parcel.application.vo.invoice.CjInvoiceData;
import lombok.Builder;

@Builder
public record CjAddrResultDto(
        String endNo,                // 도착지 코드
        String subEndNo,             // 도착지 서브코드
        String manBranNm,            // 배송집배점명
        String cldvEmpNm,            // 배송 sm 명
        String cldvEmpClsCd,         // sm 분류코드
        String shortAddr,            // 주소 약칭
        String fareDiv,              // 운임구분코드 (01: 선불, 02: 착불, 03: 신용)
        int bscFare,                 // 계약운임
        int dealFare,                // 취급운임
        int ferryFare,               // 도선운임
        String checkedSenderZipCode,
        String checkedSenderAddress,             // 주소정제 완료한 sender 기본 주소
        String checkedSenderAddressDetail,        // 주소정제 완료한 sender 상세 주소
        String checkedReceiverZipCode,
        String checkedReceiverAddress,             // 주소정제 완료한 receiver 기본 주소
        String checkedReceiverAddressDetail        // 주소정제 완료한 receiver 상세 주소
) {
    public Address toSenderAddress() {
        return Address.builder()
                .zipCode(checkedSenderZipCode)
                .address(checkedSenderAddress)
                .addressDetail(checkedSenderAddressDetail)
                .build();
    }

    public Address toReceiverAddress() {
        return Address.builder()
                .zipCode(checkedReceiverZipCode)
                .address(checkedReceiverAddress)
                .addressDetail(checkedReceiverAddressDetail)
                .build();
    }

    public Fare toFare() {
        return Fare.builder()
                .basicFare(bscFare)
                .dealFare(dealFare)
                .shipFare(ferryFare)
                .build();
    }

    public CjInvoiceData toCjInvoiceData() {
        return CjInvoiceData.builder()
                .endNo(endNo)
                .subEndNo(subEndNo)
                .manBranNm(manBranNm)
                .cldvEmpNm(cldvEmpNm)
                .cldvEmpClsCd(cldvEmpClsCd)
                .shortAddr(shortAddr)
                .build();
    }
}

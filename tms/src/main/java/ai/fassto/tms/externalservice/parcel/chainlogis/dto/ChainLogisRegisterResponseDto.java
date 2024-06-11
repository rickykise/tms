package ai.fassto.tms.externalservice.parcel.chainlogis.dto;

public record ChainLogisRegisterResponseDto(
        String bookId,                      // 두발히어로 운송장번호: 바코드 형태로 운송장 표시필요
        String orderIdFromCorp,             // 사측 주문번호: 운송장번호 대체사용 가능, 운송장번호 대체 사용 시 바코드 형태로 운송장에 표시 필요
        String dongGroup,                   // 두발히어로 지역분류 코드: 운송장에 표시 필요, 빈값일 수 있음.
        String receiverAddress,             // 고객사 입력 수하인 주소값
        String receiverAddressDetail,       // 고객사 입력 수하인 주소값, 요청값에 없는 경우 응답값에서 제외됨
        String receiverAddressCleaned,      // 고객사 입력 수하인 주소 정제 후 지번 주소, 값이 없는 경우 응답값 미포함
        String receiverAddressRoadCleaned,  // 고객사 입력 수하인 주소 정제 후 도로명 주소, 값이 없는 경우 응답값 미포함
        String receiverAddressRoadDetail,   // 고객사 입력 수하인 주소 정제 후 상세 주소, 값이 없는 경우 응답값 미포함
        String receiverAddressBuilding,     // 고객사 입력 수하인 주소 정제 후 건물명, 값이 없는 경우 응답값 미포함
        String placePageUrl                 // 수령희망위치 등 고객 요청 url
) {
}

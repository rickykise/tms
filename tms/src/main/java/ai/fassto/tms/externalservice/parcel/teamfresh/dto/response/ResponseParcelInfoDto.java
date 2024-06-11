package ai.fassto.tms.externalservice.parcel.teamfresh.dto.response;


import java.util.List;

public record ResponseParcelInfoDto(
        String idx,
        List<String> barcodeNumList,
        String orderNum,
        String customerOrderNum,
        String orderResult,
        String orderResultCode,
        String areaGroupLabel,
        String hubName,
        String tonightYn
) {
}

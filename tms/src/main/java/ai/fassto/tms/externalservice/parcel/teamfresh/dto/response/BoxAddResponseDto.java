package ai.fassto.tms.externalservice.parcel.teamfresh.dto.response;

public record BoxAddResponseDto(
        String resultCode,
        String resultMsg,
        String barcodeNum
) {
}

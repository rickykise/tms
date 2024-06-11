package ai.fassto.tms.externalservice.parcel.hanjin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record HanjinAddrRequestDto(
        @JsonProperty("address")
        String receiverFullAddress,
        @JsonProperty("snd_zip")
        String senderZipCode,
        @JsonProperty("client_id")
        String clientId
) {
    private static final String HANJIN_CUST_ID = "FASSTO";

    @Builder
    public HanjinAddrRequestDto {
        clientId = HANJIN_CUST_ID;
    }
}

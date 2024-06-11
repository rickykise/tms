package ai.fassto.tms;

import ai.fassto.tms.externalservice.parcel.hanjin.dto.HanjinAddrRequestDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.request.RequestParcelInfoDto;
import org.junit.jupiter.api.Test;

public class RecordTest {
    @Test
    void HanjinAddrRequestDto() {
        HanjinAddrRequestDto target = HanjinAddrRequestDto.builder()
                .receiverFullAddress("12345")
                .senderZipCode("67890")
                .clientId("11111").build();

        System.out.println(target);
    }

    @Test
    void RequestParcelInfoDto() {
        RequestParcelInfoDto target = RequestParcelInfoDto.builder()
                .addrDetail(null)
                .senderName("1234")
                .build();

        System.out.println(target);
    }
}


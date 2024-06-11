package ai.fassto.tms.externalservice.parcel.teamfresh.dto.request;

import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public record RequestParcelInfoDto(
        String idx,
        String customerName,
        String receiverName,
        String receiverHp,
        String addrBasic,
        String addrDetail,
        String zipCode,
        String note1,
        String note2,
        int boxCount,
        String customerOrderNum,
        String senderName,
        String itemInfo,
        String tonightYn
) {
    private static final String TONIGHT_DIVISION = "0";
    private static final String CUSTOMER_NAME = "파스토";

    @Builder
    public RequestParcelInfoDto {
        idx = UUID.randomUUID().toString();
        addrDetail = StringUtils.isBlank(addrDetail) ? " " : addrDetail;
        customerName = CUSTOMER_NAME;
        tonightYn = TONIGHT_DIVISION;
    }
}

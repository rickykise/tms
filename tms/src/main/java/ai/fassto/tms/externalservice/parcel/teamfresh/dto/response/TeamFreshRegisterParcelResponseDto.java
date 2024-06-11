package ai.fassto.tms.externalservice.parcel.teamfresh.dto.response;

import ai.fassto.tms.externalservice.parcel.teamfresh.vo.enums.ParcelResultType;
import ai.fassto.tms.externalservice.parcel.teamfresh.vo.enums.TeamFreshResultType;

import java.util.List;

public record TeamFreshRegisterParcelResponseDto(
        String resultCode,
        String resultMsg,
        List<ResponseParcelInfoDto> orderResult
) {
    public boolean isSuccess(int index) {
        return TeamFreshResultType.isSuccess(resultCode) && ParcelResultType.isSuccess(orderResult.get(index).orderResultCode());
    }

    public String errorMessage(int index) {
        if (!TeamFreshResultType.isSuccess(resultCode)) {
            return resultMsg;
        }

        return orderResult.get(index).orderResult();
    }

    public String getTeamFreshOrdNoBy(int index) {
        return this.orderResult.get(index).orderNum();
    }

    public String getAreaCodeBy(int index) {
        return this.orderResult.get(index).areaGroupLabel();
    }

    public String getBarcodeNumBy(int index, int barcodeIndex) {
        return this.orderResult.get(index).barcodeNumList().get(barcodeIndex);
    }
}

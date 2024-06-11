package ai.fassto.tms.dataaccess.common.adapter;


import ai.fassto.tms.common.util.JsonUtil;
import ai.fassto.tms.dataaccess.common.TmsApiRetryCheck;
import ai.fassto.tms.dataaccess.common.dto.TmsApiResponseDto;
import ai.fassto.tms.dataaccess.common.dto.TmsInsertApiResponseDto;
import ai.fassto.tms.dataaccess.common.dto.TmsSelectApiResponseDto;
import ai.fassto.tms.dataaccess.common.dto.TmsUpdateApiResponseDto;
import ai.fassto.tms.dataaccess.common.repository.mybatis.TmsApiMapper;
import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiStatusType;
import ai.fassto.tms.dataaccess.common.vo.enums.TmsApiType;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

;

@Component
@RequiredArgsConstructor
public class TmsApiRetryCheckHandler implements TmsApiRetryCheck {
    private final TmsApiMapper tmsApiMapper;

    @Transactional
    public ParcelPreCallResponseDto preCallAlreadyProcessedData(ParcelPreCallRequestDto requestDto) {
        final TmsApiResponseDto responseDto = this.alreadyProcessedData(requestDto.slipNo(), TmsApiType.PRE_CALL);

        if (Objects.isNull(responseDto)) {
            this.insertPreCallProcessedData(requestDto);
            return null;
        } else if (TmsApiStatusType.SUCCESS != responseDto.status()) {
            return null;
        }

        return JsonUtil.toObject(responseDto.response(), ParcelPreCallResponseDto.class);
    }

    @Transactional
    public ParcelFinalCallResponseDto finalCallAlreadyProcessedData(ParcelFinalCallRequestDto requestDto) {
        final TmsApiResponseDto responseDto = this.alreadyProcessedData(requestDto.slipNo(), TmsApiType.FINAL_CALL);

        if (Objects.isNull(responseDto)) {
            this.insertFinalCallProcessedData(requestDto);
            return null;
        } else if (TmsApiStatusType.SUCCESS != responseDto.status()) {
            return null;
        }

        return JsonUtil.toObject(responseDto.response(), ParcelFinalCallResponseDto.class);
    }

    private void insertPreCallProcessedData(ParcelPreCallRequestDto requestDto) {
        tmsApiMapper.insertTmsResponseData(this.toTmsInsertResponseDto(requestDto.slipNo(), TmsApiType.PRE_CALL, TmsApiStatusType.IN_PROGRESS));
    }

    private void insertFinalCallProcessedData(ParcelFinalCallRequestDto requestDto) {
        tmsApiMapper.insertTmsResponseData(this.toTmsInsertResponseDto(requestDto.slipNo(), TmsApiType.FINAL_CALL, TmsApiStatusType.IN_PROGRESS));
    }

    @Override
    public void updatePreCallProcessedData(ParcelPreCallResponseDto responseDto) {
        tmsApiMapper.updateTmsResponseData(this.toTmsUpdateApiResponseDto(responseDto.slipNo(), TmsApiType.PRE_CALL, TmsApiStatusType.SUCCESS, JsonUtil.toJson(responseDto)));
    }

    public void updateFinalCallProcessedData(ParcelFinalCallResponseDto responseDto) {
        tmsApiMapper.updateTmsResponseData(this.toTmsUpdateApiResponseDto(responseDto.slipNo(), TmsApiType.FINAL_CALL, TmsApiStatusType.SUCCESS, JsonUtil.toJson(responseDto)));
    }


    private TmsApiResponseDto alreadyProcessedData(String slipNo, TmsApiType apiType) {
        return tmsApiMapper.selectTmsResponseData(this.toTmsSelectResponseDto(slipNo, apiType));
    }

    private TmsSelectApiResponseDto toTmsSelectResponseDto(String slipNo, TmsApiType apiType) {
        return TmsSelectApiResponseDto.builder()
                .slipNo(slipNo)
                .apiType(apiType)
                .build();
    }

    private TmsInsertApiResponseDto toTmsInsertResponseDto(String slipNo, TmsApiType apiType, TmsApiStatusType status) {
        return TmsInsertApiResponseDto.builder()
                .slipNo(slipNo)
                .apiType(apiType)
                .status(status)
                .build();
    }

    private TmsUpdateApiResponseDto toTmsUpdateApiResponseDto(String slipNo, TmsApiType apiType, TmsApiStatusType status, String responseData) {
        return TmsUpdateApiResponseDto.builder()
                .slipNo(slipNo)
                .apiType(apiType)
                .status(status)
                .response(responseData)
                .build();
    }
}

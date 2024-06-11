package ai.fassto.tms.dataaccess.common;

import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;

public interface TmsApiRetryCheck {
    ParcelPreCallResponseDto preCallAlreadyProcessedData(ParcelPreCallRequestDto requestDto);

    ParcelFinalCallResponseDto finalCallAlreadyProcessedData(ParcelFinalCallRequestDto requestDto);

    void updatePreCallProcessedData(ParcelPreCallResponseDto responseDto);
}

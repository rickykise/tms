package ai.fassto.tms.domain.parcel.application.port.input;

import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;

public interface ParcelService {
    ParcelPreCallResponseDto preCall(ParcelPreCallRequestDto parcelPreCallRequestDto);

    ParcelFinalCallResponseDto finalCall(ParcelFinalCallRequestDto parcelFinalCallRequestDto);
}

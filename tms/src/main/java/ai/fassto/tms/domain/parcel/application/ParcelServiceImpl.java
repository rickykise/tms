package ai.fassto.tms.domain.parcel.application;

import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;
import ai.fassto.tms.domain.parcel.application.port.input.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final ParcelRequestHandler requestHandler;

    @Override
    public ParcelPreCallResponseDto preCall(ParcelPreCallRequestDto parcelPreCallRequestDto) {
        return requestHandler.preCall(parcelPreCallRequestDto);
    }

    @Override
    public ParcelFinalCallResponseDto finalCall(ParcelFinalCallRequestDto parcelFinalCallRequestDto) {
        return requestHandler.finalCall(parcelFinalCallRequestDto);
    }
}

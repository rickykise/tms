package ai.fassto.tms.application.rest.parcel.v1;

import ai.fassto.tms.dataaccess.common.TmsApiRetryCheck;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;
import ai.fassto.tms.domain.parcel.application.port.input.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "TMS PARCEL", description = "TMS 택배")
@RequestMapping("/carrier/parcel")
public class ParcelApiController {
    private final TmsApiRetryCheck tmsApiRetryCheck;
    private final ParcelService parcelService;

    @Operation(description = "가송장 채번 / 팀프레시는 택배접수")
    @PostMapping("/pre-call")
    public ParcelPreCallResponseDto preCall(@RequestBody @Valid ParcelPreCallRequestDto parcelPreCallRequestDto) {
        final ParcelPreCallResponseDto alreadyProcessedData = tmsApiRetryCheck.preCallAlreadyProcessedData(parcelPreCallRequestDto);

        if (Objects.nonNull(alreadyProcessedData)) {
            return alreadyProcessedData;
        }

        final ParcelPreCallResponseDto responseDto = parcelService.preCall(parcelPreCallRequestDto);
        tmsApiRetryCheck.updatePreCallProcessedData(responseDto);

        return responseDto;
    }

    @Operation(description = "택배접수")
    @PostMapping("/final-call")
    public ParcelFinalCallResponseDto finalCall(@RequestBody @Valid ParcelFinalCallRequestDto parcelFinalCallRequestDto) {
        return parcelService.finalCall(parcelFinalCallRequestDto);
    }
}

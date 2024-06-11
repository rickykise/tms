package ai.fassto.tms.externalservice.parcel.adapter;


import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.port.output.api.teamfresh.TeamFreshParcelApi;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.domain.parcel.core.exception.ParcelRegisterFailException;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.BoxAddInvoiceNoDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.response.BoxAddResponseDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.response.TeamFreshRegisterParcelResponseDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.mapper.TeamFreshParcelDataMapper;
import ai.fassto.tms.externalservice.parcel.teamfresh.send.api.TeamFreshParcelFeignClient;
import ai.fassto.tms.externalservice.parcel.teamfresh.vo.enums.TeamFreshResultType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamFreshParcelApiImpl implements TeamFreshParcelApi {
    private final TeamFreshParcelFeignClient teamFreshParcelFeignClient;
    private final TeamFreshParcelDataMapper teamFreshParcelDataMapper;

    @Override
    public TeamFreshInvoiceInfoDto registerTeamFreshParcel(Parcel parcel) {
        final TeamFreshRegisterParcelResponseDto responseDto = teamFreshParcelFeignClient.registerParcel(
                teamFreshParcelDataMapper.toTeamFreshRegisterParcelRequestDto(parcel)
        );

        if (!responseDto.isSuccess(0)) {
            log.error("TEAMFRESH REGISTER PARCEL ERROR {}", responseDto);
            throw new ParcelRegisterFailException(TmsResultType.PARCEL_REGISTER_ERROR, responseDto.errorMessage(0));
        }

        return teamFreshParcelDataMapper.toTeamFreshInvoiceInfoDto(responseDto, parcel.getDeliveryInfo());
    }

    @Override
    public BoxAddInvoiceNoDto addBox(InvoiceData invoiceData) {
        final BoxAddResponseDto responseDto =
                teamFreshParcelFeignClient.addBox(
                        teamFreshParcelDataMapper.toBoxAddRequestDto(invoiceData)
                );

        if (!TeamFreshResultType.isSuccess(responseDto.resultCode())) {
            log.error("TEAMFRESH BOX ADD ERROR {}", responseDto);
            throw new ParcelRegisterFailException(TmsResultType.PARCEL_REGISTER_ERROR, responseDto.resultMsg());
        }

        return teamFreshParcelDataMapper.toBoxAddInvoiceNoDto(responseDto);
    }
}

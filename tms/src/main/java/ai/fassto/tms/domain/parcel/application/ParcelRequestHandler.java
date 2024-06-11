package ai.fassto.tms.domain.parcel.application;

import ai.fassto.tms.domain.parcel.application.dto.finalcall.request.ParcelFinalCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.finalcall.response.ParcelFinalCallResponseDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.request.ParcelPreCallRequestDto;
import ai.fassto.tms.domain.parcel.application.dto.precall.response.ParcelPreCallResponseDto;
import ai.fassto.tms.domain.parcel.application.helper.CompanySpecificHelper;
import ai.fassto.tms.domain.parcel.application.helper.CompanySpecificHelperFactory;
import ai.fassto.tms.domain.parcel.application.mapper.ParcelDataMapper;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ParcelRequestHandler {
    private final ParcelDataMapper dataMapper;
    private final CompanySpecificHelperFactory factory;

    @Transactional
    public ParcelPreCallResponseDto preCall(ParcelPreCallRequestDto parcelPreCallRequestDto) {
        final Parcel parcel = dataMapper.toPreCallParcel(parcelPreCallRequestDto);
        final CompanySpecificHelper helper = factory.getHelperBy(parcel.getDeliveryInfo().getParcelCompanyType());

        helper.collectPreCallInvoice(parcel);

        return dataMapper.toParcelPreCallResponseDto(parcel);
    }

    @Transactional
    public ParcelFinalCallResponseDto finalCall(ParcelFinalCallRequestDto parcelFinalCallRequestDto) {
        final Parcel parcel = dataMapper.toFinalCallParcel(parcelFinalCallRequestDto);
        final CompanySpecificHelper helper = factory.getHelperBy(parcel.getDeliveryInfo().getParcelCompanyType());

        helper.checkFasstoDeliveryUnavailableArea(parcel);
        helper.updateContractCode(parcel);
        helper.itemNameSetting(parcel);
        helper.senderInfoSetting(parcel);

        helper.registerParcelProcess(parcel);
        helper.insertInvoiceData(parcel);

        helper.requestTrackingParcel(parcel);

        return dataMapper.toParcelFinalCallResponseDto(parcel);
    }
}

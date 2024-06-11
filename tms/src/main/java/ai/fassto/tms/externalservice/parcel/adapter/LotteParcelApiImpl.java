package ai.fassto.tms.externalservice.parcel.adapter;

import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.application.port.output.api.lotte.LotteParcelApi;
import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.domain.parcel.core.exception.AddressCheckFailException;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteAddrResponseDto;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteParcelInfoDto;
import ai.fassto.tms.externalservice.parcel.lotte.mapper.LotteParcelApiDataMapper;
import ai.fassto.tms.externalservice.parcel.lotte.send.api.LotteParcelFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LotteParcelApiImpl implements LotteParcelApi {
    private final LotteParcelFeignClient feignClient;
    private final LotteParcelApiDataMapper dataMapper;

    @Override
    public LotteParcelInfoDto checkAddress(Destination sender, Destination receiver, DeliveryInfo deliveryInfo) {
        final LotteAddrResponseDto responseDto = feignClient.checkAddress(
                dataMapper.toLotteAddrRequestDto(sender, receiver, deliveryInfo)
        );

        if (!responseDto.isSuccess()) {
            throw new AddressCheckFailException(TmsResultType.ADDRESS_CHECK_FAIL, "롯데 택배 주소정제에 실패하였습니다.");
        }

        return dataMapper.toLotteParcelInfoDto(responseDto);
    }
}

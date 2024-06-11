package ai.fassto.tms.externalservice.parcel.adapter;

import ai.fassto.tms.domain.parcel.application.port.output.api.cj.CjParcelApi;
import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrRequestParamDto;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResponseDto;
import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResultDto;
import ai.fassto.tms.externalservice.parcel.cj.mapper.CjParcelApiDataMapper;
import ai.fassto.tms.externalservice.parcel.cj.send.api.CjParcelFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CjParcelApiImpl implements CjParcelApi {
    private final CjParcelApiDataMapper dataMapper;
    private final CjParcelFeignClient feignClient;

    @Override
    public CjAddrResultDto checkAddress(Destination sender, Destination receiver, Box box) {
        final CjAddrRequestParamDto paramDto = dataMapper.toCjAddrRequestParamDto(sender, receiver, box);

        final CjAddrResponseDto responseDto = feignClient.checkCjAddress(paramDto.senderAddr(), paramDto.receiverAddr(), paramDto.boxType());
        responseDto.checkSuccess();

        return dataMapper.toCjAddrResultDto(responseDto);
    }
}

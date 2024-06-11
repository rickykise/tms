package ai.fassto.tms.externalservice.parcel.adapter;


import ai.fassto.tms.domain.parcel.application.port.output.api.chainlogis.ChainLogisParcelApi;
import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import ai.fassto.tms.domain.parcel.application.vo.Memo;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisInvoiceDataDto;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisRegisterResponseDto;
import ai.fassto.tms.externalservice.parcel.chainlogis.mapper.ChainLogisParcelDataMapper;
import ai.fassto.tms.externalservice.parcel.chainlogis.send.api.ChainLogisParcelFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChainLogisParcelApiImpl implements ChainLogisParcelApi {
    private final ChainLogisParcelFeignClient feignClient;
    private final ChainLogisParcelDataMapper dataMapper;

    @Override
    public ChainLogisInvoiceDataDto registerParcel(Destination sender, Destination receiver, FmsInfo fmsInfo, Memo memo, Box box) {
        final ChainLogisRegisterResponseDto responseDto = feignClient.registerParcel(
                dataMapper.toChainLogisRegisterRequestDto(sender, receiver, fmsInfo, memo, box)
        );

        return dataMapper.toChainLogisInvoiceData(responseDto);
    }
}

package ai.fassto.tms.externalservice.parcel.chainlogis.send.api;

import ai.fassto.tms.externalservice.parcel.chainlogis.config.ChainLogisParcelFeignConfig;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisRegisterRequestDto;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisRegisterResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "chain-logis-parcel",
        url = "${parcel.chain-logis.url}",
        configuration = ChainLogisParcelFeignConfig.class
)
public interface ChainLogisParcelFeignClient {
    @PostMapping(value = "/deliveries")
    ChainLogisRegisterResponseDto registerParcel(ChainLogisRegisterRequestDto chainLogisRegisterRequestDto);
}

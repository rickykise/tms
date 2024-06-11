package ai.fassto.tms.externalservice.parcel.hanjin.send.api;

import ai.fassto.tms.externalservice.parcel.hanjin.config.HanjinParcelFeignConfig;
import ai.fassto.tms.externalservice.parcel.hanjin.dto.HanjinAddrRequestDto;
import ai.fassto.tms.externalservice.parcel.hanjin.dto.HanjinAddrResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "hanjin-parcel",
        url = "${parcel.hanjin.url}",
        configuration = HanjinParcelFeignConfig.class
)
public interface HanjinParcelFeignClient {
    @PostMapping(path = "${parcel.hanjin.client-id}/print-wbl-sc")
    HanjinAddrResponseDto checkAddress(@RequestBody HanjinAddrRequestDto request);
}


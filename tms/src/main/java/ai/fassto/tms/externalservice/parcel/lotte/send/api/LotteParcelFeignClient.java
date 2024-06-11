package ai.fassto.tms.externalservice.parcel.lotte.send.api;

import ai.fassto.tms.externalservice.parcel.lotte.config.LotteParcelFeignConfig;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteAddrRequestDto;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteAddrResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "lotte-parcel",
        url = "${parcel.lotte.url}",
        configuration = LotteParcelFeignConfig.class
)
public interface LotteParcelFeignClient {
    @PostMapping("/api/address/newprint-info")
    LotteAddrResponseDto checkAddress(LotteAddrRequestDto requestDto);
}

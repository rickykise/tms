package ai.fassto.tms.externalservice.parcel.cj.send.api;

import ai.fassto.tms.externalservice.parcel.cj.dto.CjAddrResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "cjAddr-parcel",
        url = "${parcel.cjAddr.url}")
public interface CjParcelFeignClient {
    @PostMapping("/address/core")
    CjAddrResponseDto checkCjAddress(@RequestParam String senderAddr, @RequestParam String receiverAddr, @RequestParam String boxType);
}

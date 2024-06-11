package ai.fassto.tms.externalservice.parcel.teamfresh.send.api;

import ai.fassto.tms.externalservice.parcel.teamfresh.config.TeamFreshParcelFeignConfig;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.request.BoxAddRequestDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.request.TeamFreshRegisterParcelRequestDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.response.BoxAddResponseDto;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.response.TeamFreshRegisterParcelResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "team-fresh-parcel",
        url = "${parcel.team-fresh.url}",
        path = "/api/order",
        configuration = TeamFreshParcelFeignConfig.class
)
public interface TeamFreshParcelFeignClient {
    // 택배 접수
    @PostMapping(value = "/updateOrder")
    TeamFreshRegisterParcelResponseDto registerParcel(TeamFreshRegisterParcelRequestDto registerInvoiceRequestDto);

    @PostMapping(value = "/addBoxBarcode")
    BoxAddResponseDto addBox(BoxAddRequestDto requestDto);
}

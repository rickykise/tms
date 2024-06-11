package ai.fassto.tms.externalservice.parcel.sweettracker.send.api;

import ai.fassto.tms.externalservice.parcel.sweettracker.dto.TrackingParcelRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "sweet-tracker-parcel",
        url = "${parcel.delivery-gateway.url}"
)
public interface SweetTrackerParcelFeignClient {
    @PostMapping(path = "/sweet/add_invoice")
    void trackingParcel(TrackingParcelRequestDto requestDto);
}

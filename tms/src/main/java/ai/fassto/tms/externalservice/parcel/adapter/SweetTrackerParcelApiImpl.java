package ai.fassto.tms.externalservice.parcel.adapter;

import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.vo.Boxes;
import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import ai.fassto.tms.externalservice.parcel.sweettracker.mapper.SweetTrackerParcelApiDataMapper;
import ai.fassto.tms.externalservice.parcel.sweettracker.send.api.SweetTrackerParcelFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SweetTrackerParcelApiImpl implements SweetTrackerParcelApi {
    private final SweetTrackerParcelApiDataMapper dataMapper;
    private final SweetTrackerParcelFeignClient feignClient;

    /**
     * TODO : 배송추적기능은 일단 애러가 나더라도 넘어가는 방식으로 처리 후 운영처리 예정
     */
    @Override
    public void requestTrackingParcel(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Boxes boxes) {
        try {
            dataMapper.toTrackingParcelRequestDto(fmsInfo, deliveryInfo, boxes)
                    .forEach(feignClient::trackingParcel);
        } catch (FeignException e) {
            log.error("SWEET TRACKER ERROR {}", e.contentUTF8());
        }
    }
}

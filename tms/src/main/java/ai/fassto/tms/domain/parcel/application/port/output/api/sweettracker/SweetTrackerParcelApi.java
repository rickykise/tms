package ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker;

import ai.fassto.tms.domain.parcel.application.vo.Boxes;
import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;

public interface SweetTrackerParcelApi {
    void requestTrackingParcel(FmsInfo fmsInfo, DeliveryInfo deliveryInfo, Boxes boxes);
}

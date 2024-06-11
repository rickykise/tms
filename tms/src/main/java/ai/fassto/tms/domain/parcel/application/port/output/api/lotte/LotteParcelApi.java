package ai.fassto.tms.domain.parcel.application.port.output.api.lotte;

import ai.fassto.tms.domain.parcel.application.vo.DeliveryInfo;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.externalservice.parcel.lotte.dto.LotteParcelInfoDto;

public interface LotteParcelApi {
    LotteParcelInfoDto checkAddress(Destination sender, Destination receiver, DeliveryInfo deliveryInfo);
}

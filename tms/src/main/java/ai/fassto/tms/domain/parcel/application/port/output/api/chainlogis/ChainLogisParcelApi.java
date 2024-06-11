package ai.fassto.tms.domain.parcel.application.port.output.api.chainlogis;

import ai.fassto.tms.domain.parcel.application.vo.Box;
import ai.fassto.tms.domain.parcel.application.vo.Destination;
import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import ai.fassto.tms.domain.parcel.application.vo.Memo;
import ai.fassto.tms.externalservice.parcel.chainlogis.dto.ChainLogisInvoiceDataDto;

public interface ChainLogisParcelApi {
    ChainLogisInvoiceDataDto registerParcel(Destination sender, Destination receiver, FmsInfo fmsInfo, Memo memo, Box box);
}

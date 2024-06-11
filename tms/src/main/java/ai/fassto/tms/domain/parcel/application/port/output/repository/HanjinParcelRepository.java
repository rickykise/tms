package ai.fassto.tms.domain.parcel.application.port.output.repository;

import ai.fassto.tms.domain.parcel.application.vo.Parcel;

public interface HanjinParcelRepository {
    void checkIsRegisteredInvoice(Parcel parcel);

    void registerParcel(Parcel parcel);
}

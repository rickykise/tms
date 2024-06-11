package ai.fassto.tms.domain.parcel.application.port.output.api.hanjin;

import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;

public interface HanjinParcelApi {
    InvoiceData checkAddress(Parcel parcel);
}

package ai.fassto.tms.domain.parcel.application.port.output.api.teamfresh;

import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.externalservice.parcel.teamfresh.dto.BoxAddInvoiceNoDto;

public interface TeamFreshParcelApi {
    TeamFreshInvoiceInfoDto registerTeamFreshParcel(Parcel parcel);

    BoxAddInvoiceNoDto addBox(InvoiceData invoiceData);
}

package ai.fassto.tms.domain.parcel.application.helper;

import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.port.output.api.sweettracker.SweetTrackerParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.api.teamfresh.TeamFreshParcelApi;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.enums.ParcelCompanyType;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import org.springframework.stereotype.Component;

@Component
public class TeamFreshParcelHelper extends CompanySpecificHelper {
    private final TeamFreshParcelApi teamFreshParcelApi;

    public TeamFreshParcelHelper(FasstoParcelRepository fasstoParcelRepository, SweetTrackerParcelApi sweetTrackerParcelApi, TeamFreshParcelApi teamFreshParcelApi) {
        super(fasstoParcelRepository, sweetTrackerParcelApi);
        this.teamFreshParcelApi = teamFreshParcelApi;
    }

    @Override
    public ParcelCompanyType parcelCompanyType() {
        return ParcelCompanyType.TEAMFRESH;
    }

    @Override
    public void collectPreCallInvoice(Parcel parcel) {
        final TeamFreshInvoiceInfoDto teamFreshInvoiceInfoDto = teamFreshParcelApi.registerTeamFreshParcel(parcel);
        parcel.collectPreCallInvoice(teamFreshInvoiceInfoDto.invoiceNumber());

        super.fasstoParcelRepository.updateTeamFreshOrder(parcel, teamFreshInvoiceInfoDto);
    }

    @Override
    public void checkFasstoDeliveryUnavailableArea(Parcel parcel) {
    }

    @Override
    public void updateContractCode(Parcel parcel) {
    }

    @Override
    protected void checkIsRegisteredInvoice(Parcel parcel) {
    }

    @Override
    protected void collectFinalCallInvoice(Parcel parcel) {
    }

    @Override
    protected void registerParcel(Parcel parcel) {
        final InvoiceData invoiceData =
                super.fasstoParcelRepository.selectTeamFreshOrdInfo(parcel);
        parcel.receiveInvoiceData(invoiceData);

        parcel.getBoxes()
                .boxesNotHaveInvoice()
                .forEach(box ->
                        box.collectInvoiceNo(teamFreshParcelApi.addBox(invoiceData).invoiceNo())
                );
    }

    @Override
    public void insertInvoiceData(Parcel parcel) {
    }
}

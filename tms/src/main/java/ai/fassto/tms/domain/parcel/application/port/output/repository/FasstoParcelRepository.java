package ai.fassto.tms.domain.parcel.application.port.output.repository;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.ContractCodeDto;
import ai.fassto.tms.dataaccess.parcel.fassto.dto.ItemInfoDto;
import ai.fassto.tms.dataaccess.parcel.fassto.dto.LotteBrnclctDto;
import ai.fassto.tms.dataaccess.parcel.fassto.dto.SenderInfoDto;
import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.vo.*;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;

public interface FasstoParcelRepository {
    String collectInvoice(DeliveryInfo deliveryInfo, FmsInfo fmsInfo);

    String collectHanjinGuaranteeInvoice(DeliveryInfo deliveryInfo, FmsInfo fmsInfo);

    void updateTeamFreshOrder(Parcel parcel, TeamFreshInvoiceInfoDto teamFreshInvoiceInfoDto);

    void checkDeliveryUnavailable(FmsInfo fmsInfo, Destination receiver, DeliveryInfo deliveryInfo);

    ContractCodeDto getCenterContractCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo);

    ContractCodeDto getHanjinGuaranteeCenterContractCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo);

    ItemInfoDto getItemName(Item item);

    SenderInfoDto getSenderInfo(FmsInfo fmsInfo);

    void insertHanjinInvoiceData(Parcel parcel);

    boolean isIntegratedInvoice(FmsInfo fmsInfo);

    InvoiceData selectTeamFreshOrdInfo(Parcel parcel);

    InvoiceData selectEcremmoceParcelData(FmsInfo fmsInfo);

    void insertEcremmoceInvoiceData(Parcel parcel);

    void insertChainLogisInvoiceData(Parcel parcel);

    void insertCjInvoiceData(Parcel parcel);

    LotteBrnclctDto selectBrnclctInfo(FmsInfo fmsInfo, DeliveryInfo deliveryInfo);

    void insertLotteInvoiceData(Parcel parcel);
}

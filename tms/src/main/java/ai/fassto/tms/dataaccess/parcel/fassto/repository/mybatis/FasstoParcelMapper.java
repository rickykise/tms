package ai.fassto.tms.dataaccess.parcel.fassto.repository.mybatis;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FasstoParcelMapper {
    boolean isDeliveryUnavailable(DeliveryUnavailableDto deliveryUnavailableDto);

    Optional<InvoiceInfoDto> selectCustomInvoiceNo(SelectInvoiceInfoDto selectInvoiceInfo);

    Optional<InvoiceInfoDto> selectInvoiceNo(SelectInvoiceInfoDto selectInvoiceInfo);

    Optional<InvoiceInfoDto> selectHanjinGuaranteeCustomInvoiceNo(SelectInvoiceInfoDto selectInvoiceInfo);

    Optional<InvoiceInfoDto> selectHanjinGuaranteeInvoiceNo(SelectInvoiceInfoDto selectInvoiceInfo);

    void updateInvoiceNo(UpdateInvoiceInfoDto updateInvoiceInfo);

    void updateHanjinGuaranteeInvoiceNo(UpdateInvoiceInfoDto updateInvoiceInfo);

    Optional<ContractCodeDto> getCenterContractCode(SelectCenterContractCodeDto selectCenterContractCodeDto);

    ContractCodeDto getCustomCenterContractCode(SelectCenterContractCodeDto selectCenterContractCodeDto);

    ContractCodeDto getHanjinGuaranteeCustomCenterContractCode(SelectCenterContractCodeDto selectCenterContractCodeDto);

    Optional<ContractCodeDto> getHanjinGuaranteeCenterContractCode(SelectCenterContractCodeDto selectCenterContractCodeDto);

    void updateTeamFreshOrder(UpdateTeamFreshOrderDto updateTeamFreshOrderDto);

    Optional<ItemInfoDto> selectItemsInfo(SelectItemDto selectItemDto);

    SenderInfoDto selectSenderInfo(SelectSenderInfoDto selectSenderInfoDto);

    void insertHanjinInvoice(List<HanjinInvoiceDataDto> hanjinInvoiceDataDtos);

    boolean isIntegratedInvoice(String warehouseCode);

    Optional<TeamFreshOrderInfoDto> selectTeamFreshOrdInfo(OutOrdSlipNoDto outOrdSlipNoDto);

    WarehouseNameDto selectWareHouseNameBy(WarehouseCodeDto warehouseCodeDto);

    Optional<EcremmoceParcelDataDto> selectEcremmoceParcelData(SelectEcremmoceParcelDataDto selectEcremmoceParcelDataDto);

    void insertEcremmoceInvoiceData(EcremmoceInvoiceDataDto ecremmoceInvoiceDataDto);

    void insertChainLogisInvoice(List<ChainLogisInvoiceDataDto> chainLogisInvoiceDataDtos);

    void insertLotteInvoice(List<LotteInvoiceDataDto> lotteInvoiceDataDtos);

    void insertCjInvoice(List<CjInvoiceDataDto> cjInvoiceDataDtos);

    LotteBrnclctDto selectBrnclctInfo(SelectLotteBrnclctDto selectLotteBrnclctDto);
}

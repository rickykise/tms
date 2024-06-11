package ai.fassto.tms.dataaccess.parcel.adapter;

import ai.fassto.tms.dataaccess.parcel.fassto.dto.*;
import ai.fassto.tms.dataaccess.parcel.fassto.mapper.FasstoParcelDataAccessMapper;
import ai.fassto.tms.dataaccess.parcel.fassto.repository.mybatis.FasstoParcelMapper;
import ai.fassto.tms.domain.common.vo.enums.TmsResultType;
import ai.fassto.tms.domain.parcel.application.dto.teamfresh.TeamFreshInvoiceInfoDto;
import ai.fassto.tms.domain.parcel.application.port.output.repository.FasstoParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.*;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.domain.parcel.core.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FasstoParcelRepositoryImpl implements FasstoParcelRepository {
    private final FasstoParcelMapper fasstoParcelMapper;
    private final FasstoParcelDataAccessMapper fasstoParcelDataAccessMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String collectInvoice(DeliveryInfo deliveryInfo, FmsInfo fmsInfo) {
        final SelectInvoiceInfoDto selectInvoiceInfoDto =
                fasstoParcelDataAccessMapper.toSelectInvoiceInfo(deliveryInfo, fmsInfo);

        final InvoiceInfoDto invoiceInfoDto =
                fasstoParcelMapper.selectCustomInvoiceNo(selectInvoiceInfoDto)
                        .orElseGet(
                                () -> fasstoParcelMapper.selectInvoiceNo(selectInvoiceInfoDto)
                                        .orElseThrow(() -> new InvoiceNotCollectException(TmsResultType.LOGISTICS_INVOICE_NOT_COLLECT))
                        );

        if (invoiceInfoDto.isOverInvoiceNo()) {
            throw new InvoiceNotCollectException(TmsResultType.INVOICE_EXCEEDED);
        }

        fasstoParcelMapper.updateInvoiceNo(
                fasstoParcelDataAccessMapper.toUpdateInvoiceInfo(invoiceInfoDto, deliveryInfo)
        );

        return invoiceInfoDto.invoiceNo();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String collectHanjinGuaranteeInvoice(DeliveryInfo deliveryInfo, FmsInfo fmsInfo) {
        final SelectInvoiceInfoDto selectInvoiceInfoDto =
                fasstoParcelDataAccessMapper.toSelectInvoiceInfo(deliveryInfo, fmsInfo);

        final InvoiceInfoDto invoiceInfoDto =
                fasstoParcelMapper.selectHanjinGuaranteeCustomInvoiceNo(selectInvoiceInfoDto)
                        .orElseGet(
                                () -> fasstoParcelMapper.selectHanjinGuaranteeInvoiceNo(selectInvoiceInfoDto)
                                        .orElseThrow(() -> new InvoiceNotCollectException(TmsResultType.LOGISTICS_INVOICE_NOT_COLLECT, "N도착 보장건 송장대역대를 확인할 수 없습니다."))
                        );

        if (invoiceInfoDto.isOverInvoiceNo()) {
            throw new InvoiceNotCollectException(TmsResultType.INVOICE_EXCEEDED);
        }

        fasstoParcelMapper.updateHanjinGuaranteeInvoiceNo(
                fasstoParcelDataAccessMapper.toUpdateInvoiceInfo(invoiceInfoDto, deliveryInfo)
        );

        return invoiceInfoDto.invoiceNo();
    }

    @Override
    @Transactional
    public void updateTeamFreshOrder(Parcel parcel, TeamFreshInvoiceInfoDto teamFreshInvoiceInfoDto) {
        fasstoParcelMapper.updateTeamFreshOrder(
                fasstoParcelDataAccessMapper.toUpdateTeamFreshOrderDto(
                        parcel, teamFreshInvoiceInfoDto
                )
        );
    }

    @Override
    public void checkDeliveryUnavailable(FmsInfo fmsInfo, Destination receiver, DeliveryInfo deliveryInfo) {
        if (fasstoParcelMapper.isDeliveryUnavailable(
                fasstoParcelDataAccessMapper.toDeliveryUnavailableDto(fmsInfo, deliveryInfo, receiver.getAddress())
        )) {
            throw new ParcelRegisterFailException(TmsResultType.UNDELIVERED_PARCEL_ADDRESS);
        }
    }

    /**
     * 먼저 전략코드를 사용하는 고객사인지 확인한다.
     * 전략코드를 사용한다면 그 전략 거래처 코드를 반환해준다
     * 만약 전략코드 사용 고객사가 아니라면 일반 거래처 코드를 반환한다.
     */
    @Override
    public ContractCodeDto getCenterContractCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo) {
        final SelectCenterContractCodeDto selectCenterContractCodeDto =
                fasstoParcelDataAccessMapper.toSelectCenterContractCodeDto(fmsInfo, deliveryInfo);

        final ContractCodeDto contractCodeDto = fasstoParcelMapper.getCustomCenterContractCode(selectCenterContractCodeDto);

        if (contractCodeDto == null) {
            return fasstoParcelMapper.getCenterContractCode(selectCenterContractCodeDto)
                    .orElseThrow(() -> new ParcelDataNotFoundException(TmsResultType.ILLEGAL_ARGUMENT, "센터 계약코드를 확인할 수 없습니다."));
        }

        return contractCodeDto;
    }

    @Override
    public ContractCodeDto getHanjinGuaranteeCenterContractCode(FmsInfo fmsInfo, DeliveryInfo deliveryInfo) {
        final SelectCenterContractCodeDto selectCenterContractCodeDto =
                fasstoParcelDataAccessMapper.toSelectCenterContractCodeDto(fmsInfo, deliveryInfo);

        final ContractCodeDto contractCodeDto = fasstoParcelMapper.getHanjinGuaranteeCustomCenterContractCode(selectCenterContractCodeDto);

        if (contractCodeDto == null) {
            return fasstoParcelMapper.getHanjinGuaranteeCenterContractCode(selectCenterContractCodeDto)
                    .orElseThrow(() -> new ParcelDataNotFoundException(TmsResultType.ILLEGAL_ARGUMENT, "N도착 보장건 센터 계약코드를 확인할 수 없습니다."));
        }

        return contractCodeDto;
    }

    @Override
    public ItemInfoDto getItemName(Item item) {
        return fasstoParcelMapper.selectItemsInfo(
                fasstoParcelDataAccessMapper.toSelectItemDto(item)
        ).orElseThrow(() -> new ItemNotFoundException(TmsResultType.ILLEGAL_ARGUMENT, "상품 코드를 확인해주세요."));
    }

    @Override
    public SenderInfoDto getSenderInfo(FmsInfo fmsInfo) {
        return fasstoParcelMapper.selectSenderInfo(
                fasstoParcelDataAccessMapper.toSelectSenderInfo(fmsInfo)
        );
    }

    @Override
    @Transactional
    public void insertHanjinInvoiceData(Parcel parcel) {
        try {
            fasstoParcelMapper.insertHanjinInvoice(
                    fasstoParcelDataAccessMapper.toHanjinInvoiceDataDto(parcel)
            );
        } catch (DuplicateKeyException e) {
            throw new DuplicateInvoiceException(TmsResultType.DUPLICATE_INVOICE);
        }
    }

    @Override
    public boolean isIntegratedInvoice(FmsInfo fmsInfo) {
        return fasstoParcelMapper.isIntegratedInvoice(fmsInfo.warehouseCode());
    }

    @Override
    public InvoiceData selectTeamFreshOrdInfo(Parcel parcel) {
        final TeamFreshOrderInfoDto orderInfoDto = fasstoParcelMapper.selectTeamFreshOrdInfo(
                fasstoParcelDataAccessMapper.toOutOrdSlipNoDto(parcel)
        ).orElseThrow(() -> new ParcelDataNotFoundException(TmsResultType.NO_REGISTERED_INFORMATION, "등록된 팀프레시 주문건을 확인할 수 없습니다."));

        final WarehouseNameDto warehouseNameDto = this.selectWareHouseNameBy(parcel.getFmsInfo());

        return fasstoParcelDataAccessMapper.toTeamFreshInvoiceData(orderInfoDto, warehouseNameDto);
    }

    private WarehouseNameDto selectWareHouseNameBy(FmsInfo fmsInfo) {
        return fasstoParcelMapper.selectWareHouseNameBy(
                fasstoParcelDataAccessMapper.toWarehouseCodeDto(fmsInfo)
        );
    }

    @Override
    public InvoiceData selectEcremmoceParcelData(FmsInfo fmsInfo) {
        return fasstoParcelDataAccessMapper.toEcremmoceInvoiceData(
                fasstoParcelMapper.selectEcremmoceParcelData(
                        fasstoParcelDataAccessMapper.toSelectEcremmoceParcelDataDto(
                                fmsInfo
                        )
                ).orElseThrow(() -> new ParcelDataNotFoundException(TmsResultType.NO_REGISTERED_INFORMATION, "등록된 이크레모스 주문건을 확인할 수 없습니다."))
        );
    }

    @Override
    @Transactional
    public void insertEcremmoceInvoiceData(Parcel parcel) {
        try {
            fasstoParcelMapper.insertEcremmoceInvoiceData(
                    fasstoParcelDataAccessMapper.toEcremmoceInvoiceDataDto(parcel)
            );
        } catch (DuplicateKeyException e) {
            throw new DuplicateInvoiceException(TmsResultType.DUPLICATE_INVOICE);
        }
    }

    @Override
    @Transactional
    public void insertChainLogisInvoiceData(Parcel parcel) {
        try {
            fasstoParcelMapper.insertChainLogisInvoice(
                    fasstoParcelDataAccessMapper.toChainLogisInvoiceDataDto(parcel)
            );
        } catch (
                DuplicateKeyException e) {
            throw new DuplicateInvoiceException(TmsResultType.DUPLICATE_INVOICE);
        }
    }

    @Override
    @Transactional
    public void insertCjInvoiceData(Parcel parcel) {
        try {
            fasstoParcelMapper.insertCjInvoice(
                    fasstoParcelDataAccessMapper.toCjInvoiceDataDtos(parcel)
            );
        } catch (DuplicateKeyException e) {
            throw new DuplicateInvoiceException(TmsResultType.DUPLICATE_INVOICE);
        }
    }

    @Override
    public LotteBrnclctDto selectBrnclctInfo(FmsInfo fmsInfo, DeliveryInfo deliveryInfo) {
        return fasstoParcelMapper.selectBrnclctInfo(
                fasstoParcelDataAccessMapper.toSelectLotteBrnclctDto(fmsInfo, deliveryInfo)
        );
    }

    @Override
    @Transactional
    public void insertLotteInvoiceData(Parcel parcel) {
        try {
            fasstoParcelMapper.insertLotteInvoice(
                    fasstoParcelDataAccessMapper.toLotteInvoiceDataDtos(parcel)
            );
        } catch (DuplicateKeyException e) {
            throw new DuplicateInvoiceException(TmsResultType.DUPLICATE_INVOICE);
        }
    }
}

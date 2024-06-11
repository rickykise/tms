package ai.fassto.tms.dataaccess.parcel.adapter;

import ai.fassto.tms.dataaccess.parcel.lotte.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.lotte.mapper.LotteParcelDataAccessMapper;
import ai.fassto.tms.dataaccess.parcel.lotte.repository.mybatis.LotteParcelMapper;
import ai.fassto.tms.domain.parcel.application.port.output.repository.LotteParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class LotteParcelRepositoryImpl implements LotteParcelRepository {
    private final LotteParcelMapper mapper;
    private final LotteParcelDataAccessMapper dataAccessMapper;

    @Override
    public void checkIsRegisteredInvoice(Parcel parcel) {
        dataAccessMapper.toSelectRegisteredInvoiceDto(parcel)
                .stream()
                .map(mapper::isRegisteredInvoice)
                .forEach(CheckRegisteredInvoiceDto::checkRegisteredInvoice);
    }

    @Override
    @Transactional
    public void registerParcel(Parcel parcel) {
        dataAccessMapper.toRegisterParcelDtos(parcel)
                .forEach(mapper::insertInvoice);
    }
}

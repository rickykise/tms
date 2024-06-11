package ai.fassto.tms.dataaccess.parcel.adapter;

import ai.fassto.tms.dataaccess.parcel.cj.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.cj.mapper.CjParcelDataAccessMapper;
import ai.fassto.tms.dataaccess.parcel.cj.repository.mybatis.CjParcelMapper;
import ai.fassto.tms.domain.parcel.application.port.output.repository.CjParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CjParcelRepositoryImpl implements CjParcelRepository {
    private final CjParcelMapper mapper;
    private final CjParcelDataAccessMapper dataAccessMapper;

    @Override
    public void checkIsRegisteredInvoice(Parcel parcel) {
        dataAccessMapper.toCustomerUseNoDto(parcel)
                .stream()
                .map(mapper::isRegisteredInvoice)
                .forEach(CheckRegisteredInvoiceDto::checkRegisteredInvoice);
    }

    @Override
    @Transactional
    public void registerParcel(Parcel parcel) {
        dataAccessMapper.toCjRegisterParcelDto(parcel)
                .forEach(mapper::saveInvoice);
    }
}

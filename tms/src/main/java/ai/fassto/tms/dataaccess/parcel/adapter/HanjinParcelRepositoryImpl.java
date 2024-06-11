package ai.fassto.tms.dataaccess.parcel.adapter;

import ai.fassto.tms.dataaccess.parcel.hanjin.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.hanjin.mapper.HanjinParcelDataAccessMapper;
import ai.fassto.tms.dataaccess.parcel.hanjin.repository.mybatis.HanjinParcelMapper;
import ai.fassto.tms.domain.parcel.application.port.output.repository.HanjinParcelRepository;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class HanjinParcelRepositoryImpl implements HanjinParcelRepository {
    private final HanjinParcelMapper hanjinParcelMapper;
    private final HanjinParcelDataAccessMapper hanjinParcelDataAccessMapper;

    @Override
    public void checkIsRegisteredInvoice(Parcel parcel) {
        hanjinParcelDataAccessMapper.toCustomerUseNoDto(parcel)
                .stream()
                .map(hanjinParcelMapper::isRegisteredInvoice)
                .forEach(CheckRegisteredInvoiceDto::checkRegisteredInvoice);
    }

    @Override
    @Transactional
    public void registerParcel(Parcel parcel) {
        hanjinParcelDataAccessMapper.toRegisterParcelDto(parcel)
                .forEach(request -> {
                    hanjinParcelMapper.registerParcel(request);
                    request.checkSuccess();
                });
    }
}

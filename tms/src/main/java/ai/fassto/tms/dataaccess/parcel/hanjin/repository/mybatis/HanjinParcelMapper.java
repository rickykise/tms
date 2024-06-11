package ai.fassto.tms.dataaccess.parcel.hanjin.repository.mybatis;

import ai.fassto.tms.dataaccess.parcel.hanjin.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.hanjin.dto.CustomerUseNoDto;
import ai.fassto.tms.dataaccess.parcel.hanjin.dto.RegisterParcelDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HanjinParcelMapper {
    CheckRegisteredInvoiceDto isRegisteredInvoice(CustomerUseNoDto customerUseNoDto);

    void registerParcel(RegisterParcelDto registerParcelDto);
}

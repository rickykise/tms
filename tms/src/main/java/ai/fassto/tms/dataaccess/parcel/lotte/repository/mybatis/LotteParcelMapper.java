package ai.fassto.tms.dataaccess.parcel.lotte.repository.mybatis;

import ai.fassto.tms.dataaccess.parcel.lotte.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.lotte.dto.RegisterParcelDto;
import ai.fassto.tms.dataaccess.parcel.lotte.dto.SelectRegisteredInvoiceDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LotteParcelMapper {
    CheckRegisteredInvoiceDto isRegisteredInvoice(SelectRegisteredInvoiceDto selectRegisteredInvoiceDto);

    int insertInvoice(RegisterParcelDto registerParcelDto);
}

package ai.fassto.tms.dataaccess.parcel.cj.repository.mybatis;

import ai.fassto.tms.dataaccess.parcel.cj.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.cj.dto.CjRegisterParcelDto;
import ai.fassto.tms.dataaccess.parcel.cj.dto.CustomerUseNoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CjParcelMapper {
    CheckRegisteredInvoiceDto isRegisteredInvoice(CustomerUseNoDto customerUseNoDto);

    void saveInvoice(CjRegisterParcelDto cjRegisterParcelDto);
}

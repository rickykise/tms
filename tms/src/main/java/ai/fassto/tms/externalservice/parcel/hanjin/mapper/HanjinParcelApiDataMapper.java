package ai.fassto.tms.externalservice.parcel.hanjin.mapper;

import ai.fassto.tms.domain.parcel.application.vo.Address;
import ai.fassto.tms.domain.parcel.application.vo.invoice.HanjinInvoiceData;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.externalservice.parcel.hanjin.dto.HanjinAddrRequestDto;
import ai.fassto.tms.externalservice.parcel.hanjin.dto.HanjinAddrResponseDto;
import ai.fassto.tms.externalservice.parcel.hanjin.vo.enums.DomRgnType;
import org.springframework.stereotype.Component;

@Component
public class HanjinParcelApiDataMapper {
    // TODO : 기획단에서 아래 코드로 하드코딩 요청하였음
    // TODO : 거점센터가 추가되거나 해서 아래코드가 주소정제 결과값이 되어야할경우 수정해야함.
    private static final String S_TML_CODE = "470";
    private static final String S_TML_NAM = "안성";

    public HanjinAddrRequestDto toHanjinAddrRequestDto(Address receiver, Address sender) {
        return HanjinAddrRequestDto.builder()
                .receiverFullAddress(receiver.fullAddress())
                .senderZipCode(sender.getZipCode())
                .build();
    }

    public InvoiceData toHanjinInvoiceData(HanjinAddrResponseDto response) {
        return HanjinInvoiceData.builder()
                .sTmlNam(S_TML_NAM)
                .sTmlCod(S_TML_CODE)
                .zipCod(response.zipCod())
                .tmlNam(response.tmlNam())
                .tmlCod(response.tmlCod())
                .cenNam(response.cenNam())
                .cenCod(response.cenCod())
                .domRgn(DomRgnType.findBy(response.domRgn()).getMeans())
                .hubCod(response.hubCod())
                .domMid(response.domMid())
                .grpRnk(response.grpRnk())
                .esNam(response.esNam())
                .esCod(response.esCod())
                .build();
    }
}

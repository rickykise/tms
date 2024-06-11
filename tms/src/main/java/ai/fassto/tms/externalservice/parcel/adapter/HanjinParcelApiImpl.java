package ai.fassto.tms.externalservice.parcel.adapter;

import ai.fassto.tms.domain.parcel.application.port.output.api.hanjin.HanjinParcelApi;
import ai.fassto.tms.domain.parcel.application.vo.Parcel;
import ai.fassto.tms.domain.parcel.application.vo.invoice.InvoiceData;
import ai.fassto.tms.externalservice.parcel.hanjin.dto.HanjinAddrResponseDto;
import ai.fassto.tms.externalservice.parcel.hanjin.helper.HanjinSlackErrorReporter;
import ai.fassto.tms.externalservice.parcel.hanjin.mapper.HanjinParcelApiDataMapper;
import ai.fassto.tms.externalservice.parcel.hanjin.send.api.HanjinParcelFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HanjinParcelApiImpl implements HanjinParcelApi {
    private final HanjinParcelFeignClient hanjinParcelFeignClient;
    private final HanjinParcelApiDataMapper hanjinParcelApiDataMapper;
    private final HanjinSlackErrorReporter hanjinSlackErrorReporter;

    @Override
    public InvoiceData checkAddress(Parcel parcel) {
        final HanjinAddrResponseDto responseDto =
                hanjinParcelFeignClient.checkAddress(
                        hanjinParcelApiDataMapper
                                .toHanjinAddrRequestDto(parcel.getReceiver().getAddress(), parcel.getSender().getAddress())
                );

        if (responseDto.isStopTrading()) {
            hanjinSlackErrorReporter.reportProblem(parcel.getFmsInfo(), responseDto.resultMessage());
        }

        responseDto.checkSuccess();

        return hanjinParcelApiDataMapper.toHanjinInvoiceData(responseDto);
    }
}

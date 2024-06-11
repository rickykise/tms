package ai.fassto.tms.application.rest.status;

import ai.fassto.tms.common.annotation.NoLogging;
import ai.fassto.tms.dataaccess.parcel.cj.repository.mybatis.CjParcelMapper;
import ai.fassto.tms.dataaccess.parcel.hanjin.dto.CheckRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.hanjin.dto.CustomerUseNoDto;
import ai.fassto.tms.dataaccess.parcel.hanjin.repository.mybatis.HanjinParcelMapper;
import ai.fassto.tms.dataaccess.parcel.lotte.dto.SelectRegisteredInvoiceDto;
import ai.fassto.tms.dataaccess.parcel.lotte.repository.mybatis.LotteParcelMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "TMS STATUS", description = "TMS 상태체크")
@RequestMapping("/api/status/tms")
@NoLogging
@RequiredArgsConstructor
@Slf4j
public class StatusApiController {
    private final LotteParcelMapper lotteParcelMapper;
    private final HanjinParcelMapper hanjinParcelMapper;
    private final CjParcelMapper cjParcelMapper;

    @Value("${health-check.message}")
    String healthCheckMessage;

    @Operation(description = "TMS 상태체크")
    @GetMapping("/health")
    public String health() {
        return healthCheckMessage;
    }

    @Operation(description = "LOTTE DB 상태체크")
    @GetMapping("/health/lotte/db")
    public ai.fassto.tms.dataaccess.parcel.lotte.dto.CheckRegisteredInvoiceDto lotteDb() {
        val result = lotteParcelMapper.isRegisteredInvoice(
                SelectRegisteredInvoiceDto.builder()
                        .wkSct("001")
                        .custUseNo("1234")
                        .superCustCd("183957")
                        .build()
        );

        log.info("LOTTE DB RESULT : {}", result);

        return result;
    }

    @Operation(description = "CJ DB 상태체크")
    @GetMapping("/health/cj/db")
    public ai.fassto.tms.dataaccess.parcel.cj.dto.CheckRegisteredInvoiceDto cjDb() {
        val result = cjParcelMapper.isRegisteredInvoice(
                ai.fassto.tms.dataaccess.parcel.cj.dto.CustomerUseNoDto.builder()
                        .customerUseNo("12345").build()
        );

        log.info("CJ DB RESULT : {}", result);

        return result;
    }

    @Operation(description = "HANJIN DB 상태체크")
    @GetMapping("/health/hanjin/db")
    public CheckRegisteredInvoiceDto hanjinDb() {
        val result = hanjinParcelMapper.isRegisteredInvoice(
                CustomerUseNoDto.builder()
                        .customerUseNo("123456").build()
        );

        log.info("HANJIN DB RESULT : {}", result);

        return result;
    }
}

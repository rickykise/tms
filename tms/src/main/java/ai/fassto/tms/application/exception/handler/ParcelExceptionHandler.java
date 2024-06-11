package ai.fassto.tms.application.exception.handler;

import ai.fassto.tms.application.common.dto.ApiErrorResponse;
import ai.fassto.tms.domain.parcel.core.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ParcelExceptionHandler {
    @ExceptionHandler({AddressCheckFailException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(AddressCheckFailException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER AddressCheckFailException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({DuplicateInvoiceException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(DuplicateInvoiceException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER DuplicateInvoiceException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({InvoiceNotCollectException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(InvoiceNotCollectException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER InvoiceNotCollectException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ItemNotFoundException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER ItemNotFoundException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({ParcelCompanyNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ParcelCompanyNotFoundException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER ParcelCompanyNotFoundException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({ParcelDataNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ParcelDataNotFoundException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER ParcelDataNotFoundException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({ParcelRegisterFailException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ParcelRegisterFailException e) {
        log.error("TMS PARCEL EXCEPTION HANDLER ParcelRegisterFailException", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(e.getTmsResultType().getCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }
}

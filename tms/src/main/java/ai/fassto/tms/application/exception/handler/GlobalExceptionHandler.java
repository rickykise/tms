package ai.fassto.tms.application.exception.handler;

import ai.fassto.tms.application.common.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.error("HttpException", ex);

        return ResponseEntity
                .status(statusCode)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(String.valueOf(statusCode.value()))
                                .errorMessage(((HttpStatus) statusCode).getReasonPhrase())
                                .build()
                );
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ApiErrorResponse> exceptionHandler(Exception e) {
        log.error("TMS GLOBAL EXCEPTION HANDLER : Exception, RuntimeException ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .traceId((String) MDC.get("trace_id"))
                                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                                .errorMessage("작업이 실패하였습니다. 담당자에게 문의 부탁드립니다")
                                .build()
                );
    }
}

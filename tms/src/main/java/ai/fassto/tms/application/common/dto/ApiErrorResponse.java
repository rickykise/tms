package ai.fassto.tms.application.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApiErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime timeStamp,
        String traceId,
        String errorCode,
        String errorMessage
) {
}

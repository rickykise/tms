package ai.fassto.tms.application.rest.status;

import ai.fassto.tms.common.annotation.NoLogging;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoLogging
public class DefaultStatusApiController {
    @Operation(description = "TMS 상태체크 디폴트")
    @GetMapping("/")
    public void defaultHealth() {
    }
}

package ai.fassto.tms.application.common.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        final Info info = new Info()
                .version("v1.0.0")
                .title("TMS")
                .description("TMS API 문서");
        return new OpenAPI()
                .info(info);
    }
}

package ai.fassto.tms.externalservice.parcel.hanjin.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class HanjinParcelFeignConfig {
    @Value("${parcel.hanjin.auth-key}")
    private String authKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("x-api-key", authKey);
        };
    }
}

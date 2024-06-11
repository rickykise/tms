package ai.fassto.tms.externalservice.parcel.teamfresh.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TeamFreshParcelFeignConfig {
    @Value("${parcel.team-fresh.api-access-key}")
    private String apiAccessKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("apiaccesskey", apiAccessKey);
        };
    }
}

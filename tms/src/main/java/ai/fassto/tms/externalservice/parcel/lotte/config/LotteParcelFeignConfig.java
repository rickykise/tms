package ai.fassto.tms.externalservice.parcel.lotte.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class LotteParcelFeignConfig {
    @Value("${parcel.lotte.address-token}")
    private String authorization;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", authorization); // header Authorization 설정
        };
    }
}

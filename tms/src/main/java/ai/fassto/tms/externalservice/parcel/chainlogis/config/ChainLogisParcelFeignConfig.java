package ai.fassto.tms.externalservice.parcel.chainlogis.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ChainLogisParcelFeignConfig {
    @Value("${parcel.chain-logis.token}")
    private String chainLogisToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + chainLogisToken); // header Authorization 설정
        };
    }
}

package lk.ijse.gdse71.zoneservice.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder(FeignErrorDecoder customDecoder) {
        return customDecoder;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 2);
    }
}

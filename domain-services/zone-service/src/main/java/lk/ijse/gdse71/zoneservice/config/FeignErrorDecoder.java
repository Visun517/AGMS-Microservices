package lk.ijse.gdse71.zoneservice.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lk.ijse.gdse71.zoneservice.service.impl.ExternalAuthServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ExternalAuthServiceImpl authService;

    public FeignErrorDecoder(@Lazy ExternalAuthServiceImpl authService) {
        this.authService = authService;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401) {
            authService.refreshAccessToken();

            return new feign.RetryableException(
                    response.status(),
                    "Token expired, retrying...",
                    response.request().httpMethod(),
                    (Long) null,
                    response.request()
            );
        }
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}

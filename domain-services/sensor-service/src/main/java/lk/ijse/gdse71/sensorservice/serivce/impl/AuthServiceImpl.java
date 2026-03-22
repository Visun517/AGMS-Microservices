package lk.ijse.gdse71.sensorservice.serivce.impl;

import lk.ijse.gdse71.sensorservice.dto.AuthRequestDTO;
import lk.ijse.gdse71.sensorservice.dto.AuthResponseDTO;
import lk.ijse.gdse71.sensorservice.serivce.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final WebClient webClient;
    private String cachedToken;

    @Value("${external.iot.username}")
    private String iotUsername;

    @Value("${external.iot.password}")
    private String iotPassword;

    @Override
    public String getAccessToken() {
        if (cachedToken == null) {
            return login();
        }
        return cachedToken;
    }

    @Override
    public String login() {
        log.info("Attempting to login to External IoT API...");

        AuthRequestDTO authRequest = new AuthRequestDTO(iotUsername, iotPassword);

        try {
            AuthResponseDTO response = webClient.post()
                    .uri("/auth/login")
                    .bodyValue(authRequest)
                    .retrieve()
                    .bodyToMono(AuthResponseDTO.class)
                    .block();

            if (response != null && response.getAccessToken() != null) {
                this.cachedToken = response.getAccessToken();
                log.info("Login Successful! Token stored in cache.");
                return cachedToken;
            }
        } catch (Exception e){
            log.error("Login failed: {}", e.getMessage());
        }

        return null;
    }

    @Override
    public void clearToken() {
        this.cachedToken = null;
        log.info("Expired token cleared from cache.");
    }
}

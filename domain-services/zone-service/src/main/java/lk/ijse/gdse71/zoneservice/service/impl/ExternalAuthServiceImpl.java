package lk.ijse.gdse71.zoneservice.service.impl;

import lk.ijse.gdse71.zoneservice.dto.*;
import lk.ijse.gdse71.zoneservice.service.ExternalAuthService;
import lk.ijse.gdse71.zoneservice.service.IotClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalAuthServiceImpl implements ExternalAuthService {

    private final IotClient iotClient;

    @Value("${iot.external.username}")
    private String username;

    @Value("${iot.external.password}")
    private String password;

    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        if (accessToken == null) {
            login();
        }
        return "Bearer " + accessToken;
    }

    private void login() {
        AuthResponse response = iotClient.login(new AuthRequest(username, password));
        this.accessToken = response.getAccessToken();
        this.refreshToken = response.getRefreshToken();
    }

    public void refreshAccessToken() {
        try {
            AuthResponse response = iotClient.refreshToken(new RefreshRequestDTO(refreshToken));
            this.accessToken = response.getAccessToken();
            if (response.getRefreshToken() != null) {
                this.refreshToken = response.getRefreshToken();
            }
        } catch (Exception e) {
            login();
        }
    }

}

package lk.ijse.gdse71.zoneservice.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String name;
    private String accessToken;
    private String refreshToken;
}

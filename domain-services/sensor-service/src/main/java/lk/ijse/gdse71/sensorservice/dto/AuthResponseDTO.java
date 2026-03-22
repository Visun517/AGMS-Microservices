package lk.ijse.gdse71.sensorservice.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String name;
    private String accessToken;
    private String refreshToken;
}

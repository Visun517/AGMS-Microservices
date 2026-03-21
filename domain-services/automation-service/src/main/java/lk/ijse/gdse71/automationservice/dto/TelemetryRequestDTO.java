package lk.ijse.gdse71.automationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryRequestDTO {
    private String zoneId;
    private Double currentTemp;
    private Double currentHumidity;
}
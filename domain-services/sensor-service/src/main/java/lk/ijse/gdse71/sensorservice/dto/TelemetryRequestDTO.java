package lk.ijse.gdse71.sensorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelemetryRequestDTO {
    private String zoneId;
    private Double currentTemp;
    private Double currentHumidity;
}

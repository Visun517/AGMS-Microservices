package lk.ijse.gdse71.sensorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalTelemetryResponseDTO {
    private String deviceId;
    private String zoneId;
    private TelemetryValue value;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TelemetryValue {
        private Double temperature;
        private String tempUnit;
        private Double humidity;
        private String humidityUnit;
    }
}
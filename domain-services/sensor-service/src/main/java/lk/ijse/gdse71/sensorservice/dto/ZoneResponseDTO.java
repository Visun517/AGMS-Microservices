package lk.ijse.gdse71.sensorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZoneResponseDTO {
    private Long id;
    private String name;
    private double minTemp;
    private double maxTemp;
    private String deviceId;
}
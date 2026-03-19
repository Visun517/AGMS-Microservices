package lk.ijse.gdse71.zoneservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ZoneRequestDTO {
    @NotBlank(message = "Zone name is required")
    private String name;

    @NotNull(message = "Min temperature is required")
    private Double minTemp;

    @NotNull(message = "Max temperature is required")
    private Double maxTemp;
}

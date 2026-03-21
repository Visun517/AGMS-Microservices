package lk.ijse.gdse71.automationservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZoneResponseDTO {
    private Long id;
    private String name;
    private Double minTemp;
    private Double maxTemp;

}
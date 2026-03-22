package lk.ijse.gdse71.cropservice.dto;

import lk.ijse.gdse71.cropservice.entity.types.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropResponseDTO {
    private String id;
    private String cropName;
    private Integer quantity;
    private CropStatus status;
    private LocalDateTime plantedDate;
    private LocalDateTime lastUpdated;
}

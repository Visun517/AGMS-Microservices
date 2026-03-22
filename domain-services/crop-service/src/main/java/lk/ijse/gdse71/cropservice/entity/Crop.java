package lk.ijse.gdse71.cropservice.entity;

import lk.ijse.gdse71.cropservice.entity.types.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "crops")
public class Crop {
    @Id
    private String id;
    private String cropName;
    private Integer quantity;
    private CropStatus status;
    private LocalDateTime plantedDate;
    private LocalDateTime lastUpdated;
}

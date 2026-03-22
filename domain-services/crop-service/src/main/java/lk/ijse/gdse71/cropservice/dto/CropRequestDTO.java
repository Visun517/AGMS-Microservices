package lk.ijse.gdse71.cropservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropRequestDTO {
    @NotBlank(message = "Crop name is required")
    private String cropName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}

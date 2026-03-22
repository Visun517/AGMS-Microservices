package lk.ijse.gdse71.cropservice.controller;

import lk.ijse.gdse71.cropservice.dto.ApiResponseDto;
import lk.ijse.gdse71.cropservice.dto.CropRequestDTO;
import lk.ijse.gdse71.cropservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping()
    public ResponseEntity<ApiResponseDto> createCrop(@RequestBody CropRequestDTO cropResponseDto) {
        return ResponseEntity.ok(
                new ApiResponseDto(201, "Crop created successfully", cropService.createCrop(cropResponseDto))
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponseDto> updateCrop(@PathVariable String id) {
        return ResponseEntity.ok(
                new ApiResponseDto(200, "Crop updated successfully", cropService.updateCrop(id))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto> getAllCrops() {
        return ResponseEntity.ok(
                new ApiResponseDto(200, "Crops fetched successfully", cropService.getAllCrops())
        );
    }
}

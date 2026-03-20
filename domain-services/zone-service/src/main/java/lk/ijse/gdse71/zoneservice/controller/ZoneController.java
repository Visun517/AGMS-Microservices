package lk.ijse.gdse71.zoneservice.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse71.zoneservice.dto.ApiResponseDto;
import lk.ijse.gdse71.zoneservice.dto.ZoneRequestDTO;
import lk.ijse.gdse71.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    // 1. Create Zone (POST)
    @PostMapping
    public ResponseEntity<ApiResponseDto> createZone(@Valid @RequestBody ZoneRequestDTO request) {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        201,
                        "Zone create successfully",
                        zoneService.createZone(request)
                ));

    }

    // 2. Get All Zones (GET)
    @GetMapping
    public ResponseEntity<ApiResponseDto> getAllZones() {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        200,
                        "Get all zones successfully",
                        zoneService.getAllZones()
                )
        );
    }

    // 3. Get Specific Zone (GET by ID)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getZoneById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        200,
                        "Zone get successfully",
                        zoneService.getZoneById(id)
                ));
    }

    // 4. Update Zone (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updateZone(@PathVariable Long id, @Valid @RequestBody ZoneRequestDTO request) {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        200,
                        "Zone updated successfully",
                        zoneService.updateZone(id, request)
                ));
    }

    // 5. Delete Zone (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteZone(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        200,
                        "Zone deleted successfully",
                        zoneService.deleteZone(id)

                ));
    }
}

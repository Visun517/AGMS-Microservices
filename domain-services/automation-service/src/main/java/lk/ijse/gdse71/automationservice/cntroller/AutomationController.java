package lk.ijse.gdse71.automationservice.cntroller;

import lk.ijse.gdse71.automationservice.dto.ApiResponseDto;
import lk.ijse.gdse71.automationservice.dto.TelemetryRequestDTO;
import lk.ijse.gdse71.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/automation")
@RequiredArgsConstructor
public class AutomationController {

    private final AutomationService automationService;

    @PostMapping("/process")
    public ResponseEntity<ApiResponseDto> processTelemetry(@RequestBody TelemetryRequestDTO request) {
        String result = automationService.processTelemetry(request);
        return ResponseEntity.ok(
                new ApiResponseDto(200, "Telemetry processed successfully", result)
        );
    }

    @GetMapping("/logs")
    public ResponseEntity<ApiResponseDto> getAllLogs() {
        return ResponseEntity.ok(
                new ApiResponseDto(200, "Logs fetched successfully", automationService.getAllLogs())
        );
    }
}
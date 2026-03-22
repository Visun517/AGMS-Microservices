package lk.ijse.gdse71.sensorservice.client;

import lk.ijse.gdse71.sensorservice.dto.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "zone-service")
public interface ZoneClient {
    @GetMapping("/api/zones")
    ApiResponseDto getAllZones();
}

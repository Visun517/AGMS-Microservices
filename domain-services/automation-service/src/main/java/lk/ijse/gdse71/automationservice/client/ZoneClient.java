package lk.ijse.gdse71.automationservice.client;

import lk.ijse.gdse71.automationservice.dto.ApiResponseDto;
import lk.ijse.gdse71.automationservice.dto.ZoneResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zone-service" , url = "http://localhost:8081")
public interface ZoneClient {
    @GetMapping("/api/zones/{id}")
    ApiResponseDto getZoneById(@PathVariable("id") String id);
}

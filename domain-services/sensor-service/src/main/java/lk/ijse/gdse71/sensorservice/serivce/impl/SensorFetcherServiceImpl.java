package lk.ijse.gdse71.sensorservice.serivce.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.gdse71.sensorservice.client.AutomationClient;
import lk.ijse.gdse71.sensorservice.client.ZoneClient;
import lk.ijse.gdse71.sensorservice.dto.ApiResponseDto;
import lk.ijse.gdse71.sensorservice.dto.ExternalTelemetryResponseDTO;
import lk.ijse.gdse71.sensorservice.dto.TelemetryRequestDTO;
import lk.ijse.gdse71.sensorservice.dto.ZoneResponseDTO;
import lk.ijse.gdse71.sensorservice.serivce.AuthService;
import lk.ijse.gdse71.sensorservice.serivce.SensorFetcherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorFetcherServiceImpl implements SensorFetcherService {

    private final AuthService authService;
    private final WebClient webClient;
    private final ZoneClient zoneClient;
    private final AutomationClient automationClient;

    @Value("${external.iot.endpoints.telemetry}")
    private String telemetryEndpoint;

    @Scheduled(fixedRate = 10000) // 10 seconds
    public void fetchAndPushTelemetry() {
        log.info("Starting scheduled telemetry fetch...");

        String token = authService.getAccessToken();

        if (token == null) {
            log.error("Failed to retrieve access token. Aborting telemetry fetch.");
            return;
        }
        ApiResponseDto zonesResponse = zoneClient.getAllZones();
        ObjectMapper mapper = new ObjectMapper();

        List<ZoneResponseDTO> zones = mapper.convertValue(
                zonesResponse.getData(),
                new TypeReference<List<ZoneResponseDTO>>() {});

        for (ZoneResponseDTO zone : zones) {
            try {
                ExternalTelemetryResponseDTO externalData = webClient.get()
                        .uri(telemetryEndpoint, zone.getDeviceId())
                        .header("Authorization", "Bearer " + token)
                        .retrieve()
                        .bodyToMono(ExternalTelemetryResponseDTO.class)
                        .block();

                if (externalData != null) {
                    TelemetryRequestDTO internalRequest = TelemetryRequestDTO.builder()
                            .zoneId(zone.getId().toString())
                            .currentTemp(externalData.getValue().getTemperature())
                            .currentHumidity(externalData.getValue().getHumidity())
                            .build();

                    automationClient.sendTelemetry(internalRequest);
                    log.info("Telemetry pushed for Zone: {}", zone.getName());
                }
            } catch (WebClientResponseException.Unauthorized e) {
                log.warn("Token expired for device {}. Clearing token...", zone.getDeviceId());
                authService.clearToken();
                break;
            } catch (Exception e) {
                log.error("Error fetching data for zone {}: {}", zone.getName(), e.getMessage());
            }

        }
    }
}

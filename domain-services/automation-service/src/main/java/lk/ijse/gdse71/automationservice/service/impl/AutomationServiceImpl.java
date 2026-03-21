package lk.ijse.gdse71.automationservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lk.ijse.gdse71.automationservice.client.ZoneClient;
import lk.ijse.gdse71.automationservice.dto.ApiResponseDto;
import lk.ijse.gdse71.automationservice.dto.TelemetryRequestDTO;
import lk.ijse.gdse71.automationservice.dto.ZoneResponseDTO;
import lk.ijse.gdse71.automationservice.entity.AutomationLog;
import lk.ijse.gdse71.automationservice.repository.AutomationRepository;
import lk.ijse.gdse71.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutomationServiceImpl implements AutomationService {

    private final AutomationRepository repository;
    private final ZoneClient zoneClient;

    @Override
    public String processTelemetry(TelemetryRequestDTO telemetryRequest) {
        ApiResponseDto response = zoneClient.getZoneById(telemetryRequest.getZoneId());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        ZoneResponseDTO thresholds = mapper.convertValue(response.getData(), ZoneResponseDTO.class);

        String action = "STABLE";
        Double currentTemp = telemetryRequest.getCurrentTemp();


        if (currentTemp > thresholds.getMaxTemp()) {
            action = "TURN_FAN_ON";
        } else if (currentTemp < thresholds.getMinTemp()) {
            action = "TURN_HEATER_ON";
        }

        log.info("Decision for Zone {}: {}", telemetryRequest.getZoneId(), action);

        AutomationLog logEntry = AutomationLog.builder()
                .zoneId(telemetryRequest.getZoneId())
                .tempAtThatTime(currentTemp)
                .actionTriggered(action)
                .timestamp(LocalDateTime.now())
                .build();

        repository.save(logEntry);

        return action;
    }


    @Override
    public List<AutomationLog> getAllLogs() {
        return repository.findAll();
    }
}

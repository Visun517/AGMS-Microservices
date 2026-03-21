package lk.ijse.gdse71.automationservice.service;

import lk.ijse.gdse71.automationservice.dto.TelemetryRequestDTO;
import lk.ijse.gdse71.automationservice.entity.AutomationLog;

import java.util.List;

public interface AutomationService {
    String processTelemetry(TelemetryRequestDTO telemetryRequest);
    List<AutomationLog> getAllLogs();
}

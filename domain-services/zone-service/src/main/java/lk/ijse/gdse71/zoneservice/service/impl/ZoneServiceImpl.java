package lk.ijse.gdse71.zoneservice.service.impl;

import lk.ijse.gdse71.zoneservice.dto.*;
import lk.ijse.gdse71.zoneservice.entity.Zone;
import lk.ijse.gdse71.zoneservice.exception.ValidationMinAndManTime;
import lk.ijse.gdse71.zoneservice.repository.ZoneRepository;
import lk.ijse.gdse71.zoneservice.service.IotClient;
import lk.ijse.gdse71.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;
    private final IotClient iotClient;
    private final ExternalAuthServiceImpl authService;

    @Override
    @Transactional
    public ZoneResponseDTO createZone(ZoneRequestDTO request) {

        if (request.getMinTemp() >= request.getMaxTemp()) {
            throw new ValidationMinAndManTime("Minimum temperature must be less than maximum temperature");
        }

        Zone zone = Zone.builder()
                .name(request.getName())
                .minTemp(request.getMinTemp())
                .maxTemp(request.getMaxTemp())
                .deviceId(null)
                .build();

        Zone initialSavedZone = repository.save(zone);

        String token = authService.getAccessToken();

        String formattedZoneId = "ZONE-" + initialSavedZone.getId();

        DeviceRequestDTO deviceReq = new DeviceRequestDTO(request.getName(), formattedZoneId);
        DeviceResponseDTO deviceRes = iotClient.addDevice(token, deviceReq);

        initialSavedZone.setDeviceId(deviceRes.getDeviceId());
        Zone finalSavedZone = repository.save(initialSavedZone);

        return ZoneResponseDTO.builder()
                .id(finalSavedZone.getId())
                .name(finalSavedZone.getName())
                .minTemp(finalSavedZone.getMinTemp())
                .maxTemp(finalSavedZone.getMaxTemp())
                .deviceId(finalSavedZone.getDeviceId())
                .build();
    }

    @Override
    public ZoneResponseDTO getZoneById(Long id) {
        return null;
    }

    @Override
    public List<ZoneResponseDTO> getAllZones() {
        return List.of();
    }

    @Override
    public ZoneResponseDTO updateZone(Long id, ZoneRequestDTO request) {
        return null;
    }

    @Override
    public String deleteZone(Long id) {
        return "";
    }
}

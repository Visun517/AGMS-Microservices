package lk.ijse.gdse71.zoneservice.service.impl;

import lk.ijse.gdse71.zoneservice.dto.*;
import lk.ijse.gdse71.zoneservice.entity.Zone;
import lk.ijse.gdse71.zoneservice.exception.ValidationMinAndManTime;
import lk.ijse.gdse71.zoneservice.exception.ZoneNoteFound;
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
        Zone zone = repository.findById(id)
                .orElseThrow(() -> new ZoneNoteFound("Zone not found with id: " + id));

        return ZoneResponseDTO.builder()
                .id(zone.getId())
                .name(zone.getName())
                .minTemp(zone.getMinTemp())
                .maxTemp(zone.getMaxTemp())
                .deviceId(zone.getDeviceId())
                .build();
    }

    @Override
    public List<ZoneResponseDTO> getAllZones() {
        List<Zone> zones = repository.findAll();

        return zones.stream()
                .map(zone -> ZoneResponseDTO.builder()
                        .id(zone.getId())
                        .name(zone.getName())
                        .minTemp(zone.getMinTemp())
                        .maxTemp(zone.getMaxTemp())
                        .deviceId(zone.getDeviceId())
                        .build())
                .toList();
    }

    @Override
    public ZoneResponseDTO updateZone(Long id, ZoneRequestDTO request) {
        Zone zone = repository.findById(id)
                .orElseThrow(() -> new ZoneNoteFound("Zone not found with id: " + id));

        if (request.getMinTemp() >= request.getMaxTemp()) {
            throw new ValidationMinAndManTime("Minimum temperature must be less than maximum temperature");
        }

        zone.setName(request.getName());
        zone.setMinTemp(request.getMinTemp());
        zone.setMaxTemp(request.getMaxTemp());

        Zone updatedZone = repository.save(zone);

        return ZoneResponseDTO.builder()
                .id(updatedZone.getId())
                .name(updatedZone.getName())
                .minTemp(updatedZone.getMinTemp())
                .maxTemp(updatedZone.getMaxTemp())
                .deviceId(updatedZone.getDeviceId())
                .build();
    }

    @Override
    @Transactional
    public String deleteZone(Long id) {
        if (!repository.existsById(id)) {
            throw new ZoneNoteFound("Zone not found with id: " + id);
        }

        repository.deleteById(id);
        return "Zone with id " + id + " has been deleted successfully!";
    }
}

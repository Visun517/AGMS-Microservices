package lk.ijse.gdse71.zoneservice.service;

import lk.ijse.gdse71.zoneservice.dto.ZoneRequestDTO;
import lk.ijse.gdse71.zoneservice.dto.ZoneResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ZoneService {
    ZoneResponseDTO createZone(ZoneRequestDTO request);
    ZoneResponseDTO getZoneById(Long id);
    List<ZoneResponseDTO> getAllZones();
    ZoneResponseDTO updateZone(Long id, ZoneRequestDTO request);
    String deleteZone(Long id);
}

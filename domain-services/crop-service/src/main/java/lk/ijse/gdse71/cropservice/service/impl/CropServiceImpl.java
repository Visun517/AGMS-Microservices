package lk.ijse.gdse71.cropservice.service.impl;

import lk.ijse.gdse71.cropservice.dto.CropRequestDTO;
import lk.ijse.gdse71.cropservice.dto.CropResponseDTO;
import lk.ijse.gdse71.cropservice.entity.Crop;
import lk.ijse.gdse71.cropservice.entity.types.CropStatus;
import lk.ijse.gdse71.cropservice.repository.CropRepository;
import lk.ijse.gdse71.cropservice.service.CropService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;

    @Override
    public String createCrop(CropRequestDTO dto) {
        Crop crop = Crop.builder()
                .cropName(dto.getCropName())
                .quantity(dto.getQuantity())
                .status(CropStatus.SEEDLING)
                .plantedDate(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        Crop savedCrop = cropRepository.save(crop);
        log.info("New crop batch registered: {} with ID: {}", savedCrop.getCropName(), savedCrop.getId());

        return "New batch " + savedCrop.getCropName() + " (ID: " + savedCrop.getId() + ") registered as SEEDLING.";    }

    @Override
    public String updateCrop(String id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with ID: " + id));

        CropStatus currentStatus = crop.getStatus();
        CropStatus nextStatus;

        switch (currentStatus) {
            case SEEDLING:
                nextStatus = CropStatus.VEGETATIVE;
                break;
            case VEGETATIVE:
                nextStatus = CropStatus.HARVESTED;
                break;
            case HARVESTED:
                throw new RuntimeException("Crop batch is already HARVESTED. Cannot update status further.");
            default:
                throw new IllegalStateException("Unexpected status: " + currentStatus);
        }

        crop.setStatus(nextStatus);
        crop.setLastUpdated(LocalDateTime.now());
        cropRepository.save(crop);

        log.info("Crop ID: {} transitioned from {} to {}", id, currentStatus, nextStatus);
        return "Status updated from " + currentStatus + " to " + nextStatus;
    }

    @Override
    public List<CropResponseDTO> getAllCrops() {
        List<Crop> crops = cropRepository.findAll();

        return crops.stream().map(crop -> CropResponseDTO.builder()
                .id(crop.getId())
                .cropName(crop.getCropName())
                .quantity(crop.getQuantity())
                .status(crop.getStatus())
                .plantedDate(crop.getPlantedDate())
                .lastUpdated(crop.getLastUpdated())
                .build()
        ).collect(Collectors.toList());
    }
}

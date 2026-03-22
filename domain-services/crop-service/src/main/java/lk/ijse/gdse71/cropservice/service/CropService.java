package lk.ijse.gdse71.cropservice.service;

import lk.ijse.gdse71.cropservice.dto.CropRequestDTO;
import lk.ijse.gdse71.cropservice.dto.CropResponseDTO;

import java.util.List;

public interface CropService {
    String createCrop(CropRequestDTO cropResponseDto);
    String updateCrop(String id);
    List<CropResponseDTO> getAllCrops();
}

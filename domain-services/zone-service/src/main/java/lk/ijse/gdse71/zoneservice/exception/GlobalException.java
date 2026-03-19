package lk.ijse.gdse71.zoneservice.exception;

import lk.ijse.gdse71.zoneservice.dto.ApiResponseDto;

public class GlobalException {

    public ApiResponseDto handleMinAndMaxTimeException (Exception e){
        return new ApiResponseDto(400, "Validation Error: minTemp < maxTemp", null);
    }
}

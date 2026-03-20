package lk.ijse.gdse71.zoneservice.exception;

import lk.ijse.gdse71.zoneservice.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalException {

    @ExceptionHandler(ValidationMinAndManTime.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto handleMinAndMaxTimeException (Exception e){
        return new ApiResponseDto(400, "Validation Error: minTemp < maxTemp", null);
    }
    @ExceptionHandler(ValidationMinAndManTime.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseDto handleZoneNotFoundException (Exception e){
        return new ApiResponseDto(404, "Zone not found", null);
    }

}

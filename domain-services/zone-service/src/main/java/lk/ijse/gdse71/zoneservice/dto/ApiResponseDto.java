package lk.ijse.gdse71.zoneservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ApiResponseDto {
    private int status;
    private String message;
    private Object data;

}

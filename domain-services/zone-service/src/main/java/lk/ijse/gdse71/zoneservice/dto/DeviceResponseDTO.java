package lk.ijse.gdse71.zoneservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponseDTO {
    private String deviceId;
    private String name;
    private String zoneId;
    private String userId;
    private Date createdAt;
}

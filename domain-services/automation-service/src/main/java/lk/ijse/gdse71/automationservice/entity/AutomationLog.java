package lk.ijse.gdse71.automationservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "automation_logs")
@Data
@Builder
public class AutomationLog {
    @Id
    private String id;
    private String zoneId;
    private Double tempAtThatTime;
    private String actionTriggered;
    private LocalDateTime timestamp;
}

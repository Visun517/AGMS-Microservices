package lk.ijse.gdse71.automationservice.repository;

import lk.ijse.gdse71.automationservice.entity.AutomationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutomationRepository extends MongoRepository<AutomationLog, String> {
}
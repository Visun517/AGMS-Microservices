package lk.ijse.gdse71.cropservice.repository;

import lk.ijse.gdse71.cropservice.entity.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends MongoRepository<Crop, String> {
}

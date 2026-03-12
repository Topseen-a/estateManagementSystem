package data.repositories;

import data.models.GatePass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatePassRepository extends MongoRepository<GatePass, String> {

}
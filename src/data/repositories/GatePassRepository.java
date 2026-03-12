package data.repositories;

import data.models.GatePass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GatePassRepository extends MongoRepository<GatePass, String> {

    Optional<GatePass> findById(String id);
}
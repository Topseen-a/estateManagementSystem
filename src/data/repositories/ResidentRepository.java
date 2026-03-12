package data.repositories;

import data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ResidentRepository extends MongoRepository<Resident, String> {

    Resident existsByIdOrPhoneNumber(String id, String phoneNumber);
    Optional<Resident> findById(String id);
}
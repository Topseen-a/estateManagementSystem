package com.estate.data.repositories;

import com.estate.data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ResidentRepository extends MongoRepository<Resident, String> {

    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Resident> findByPhoneNumber(String phoneNumber);
}

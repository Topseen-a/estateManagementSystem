package com.estate.data.repositories;

import com.estate.data.models.GatePass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GatePassRepository extends MongoRepository<GatePass, String> {

    Optional<GatePass> findByCode(String code);
}

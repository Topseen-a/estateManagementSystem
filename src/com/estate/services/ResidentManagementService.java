package com.estate.services;

import com.estate.data.models.Resident;
import com.estate.data.repositories.ResidentRepository;
import com.estate.exceptions.ResidentAlreadyRegisteredException;
import com.estate.exceptions.ResidentDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentManagementService {

    private final ResidentRepository residentRepository;

    public Resident registerResident(Resident resident) {
        if (residentRepository.existsByEmailOrPhoneNumber(resident.getEmail(), resident.getPhoneNumber())) {
            throw new ResidentAlreadyRegisteredException("Resident already exists");
        }
        return residentRepository.save(resident);
    }

    public Resident findResidentByPhoneNumber(String phoneNumber) {
        return residentRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResidentDoesNotExistException("Resident not found"));
    }

    public Resident findResidentById(String id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new ResidentDoesNotExistException("Resident not found"));
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public Resident updateResidentStatus(String residentId, boolean isEnabled) {
        Resident resident = findResidentById(residentId);
        resident.setEnabled(isEnabled);
        return residentRepository.save(resident);
    }
}

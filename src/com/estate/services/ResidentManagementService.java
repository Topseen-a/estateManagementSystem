package com.estate.services;

import com.estate.data.models.Resident;
import com.estate.data.repositories.ResidentRepository;
import com.estate.dtos.requests.OnboardResidentRequest;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.exceptions.ResidentAlreadyRegisteredException;
import com.estate.exceptions.ResidentDoesNotExistException;
import com.estate.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentManagementService {

    private final ResidentRepository residentRepository;
    private final Mapper mapper;

    public OnboardResidentResponse registerResident(OnboardResidentRequest request) {

        if (residentRepository.existsByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber())) {
            throw new ResidentAlreadyRegisteredException("Resident already exists");
        }

        Resident resident = new Resident();
        resident.setName(request.getName());
        resident.setEmail(request.getEmail());
        resident.setPhoneNumber(request.getPhoneNumber());
        resident.setEnabled(true);
        resident.setDateRegistered(LocalDateTime.now());

        Resident savedResident = residentRepository.save(resident);

        return mapper.toOnboardResidentResponse(savedResident);
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

    public void deleteResident(String residentId) {
        Resident resident = findResidentById(residentId);
        residentRepository.delete(resident);
    }
}
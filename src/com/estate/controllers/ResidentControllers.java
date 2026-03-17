package com.estate.controllers;

import com.estate.data.models.Resident;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.estate.services.ResidentManagementService;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentControllers {

    private final ResidentManagementService residentManagementService;

    @Autowired
    public ResidentControllers(ResidentManagementService residentManagementService) {
        this.residentManagementService = residentManagementService;
    }

    @GetMapping
    public List<Resident> getAllResidents() {
        return residentManagementService.getAllResidents();
    }

    @GetMapping("/phone")
    public Resident getResidentByPhoneNumber(@RequestParam String phoneNumber) {
        return residentManagementService.findResidentByPhoneNumber(phoneNumber);
    }

    @PostMapping
    public OnboardResidentResponse registerResident(@RequestBody Resident resident) {
        Resident registeredResident = residentManagementService.registerResident(resident);

        return new Mapper().toOnboardResidentResponse(registeredResident);
    }

    @PutMapping("/{residentId}")
    public OnboardResidentResponse updateResidentStatus(@PathVariable String residentId, @RequestParam boolean isEnabled) {
        Resident updatedResident = residentManagementService.updateResidentStatus(residentId, isEnabled);

        return new Mapper().toOnboardResidentResponse(updatedResident);
    }
}

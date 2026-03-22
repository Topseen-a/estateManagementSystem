package com.estate.controllers;

import com.estate.data.models.Resident;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.mapper.Mapper;
import com.estate.services.ResidentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final ResidentManagementService residentService;
    private final Mapper mapper;

    @PostMapping("/register")
    public ResponseEntity<OnboardResidentResponse> registerResident(@RequestBody Resident resident) {
        Resident savedResident = residentService.registerResident(resident);
        return ResponseEntity.ok(mapper.toOnboardResidentResponse(savedResident));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OnboardResidentResponse> getResidentById(@PathVariable String id) {
        Resident resident = residentService.findResidentById(id);
        return ResponseEntity.ok(mapper.toOnboardResidentResponse(resident));
    }

    @GetMapping
    public ResponseEntity<List<Resident>> getAllResidents() {
        return ResponseEntity.ok(residentService.getAllResidents());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OnboardResidentResponse> updateResidentStatus(@PathVariable String id, @RequestParam boolean isEnabled) {
        Resident resident = residentService.updateResidentStatus(id, isEnabled);
        return ResponseEntity.ok(mapper.toOnboardResidentResponse(resident));
    }

    @GetMapping("/by-phone")
    public ResponseEntity<OnboardResidentResponse> getResidentByPhoneNumber(@RequestParam String phoneNumber) {
        Resident resident = residentService.findResidentByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(mapper.toOnboardResidentResponse(resident));
    }
}
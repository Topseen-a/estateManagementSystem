package com.estate.controllers;

import com.estate.data.models.Resident;
import com.estate.dtos.requests.OnboardResidentRequest;
import com.estate.dtos.responses.ApiResponse;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.exceptions.ResidentAlreadyRegisteredException;
import com.estate.exceptions.ResidentDoesNotExistException;
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
    public ResponseEntity<ApiResponse> registerResident(@RequestBody OnboardResidentRequest request) {
        try {
            OnboardResidentResponse response = residentService.registerResident(request);
            return ResponseEntity.ok(new ApiResponse("Resident registered successfully", true, response));
        } catch (ResidentAlreadyRegisteredException ex) {
            return ResponseEntity.status(409).body(new ApiResponse(ex.getMessage(), false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getResidentById(@PathVariable String id) {
        try {
            Resident resident = residentService.findResidentById(id);
            return ResponseEntity.ok(new ApiResponse("Resident fetched successfully", true, mapper.toOnboardResidentResponse(resident)));
        } catch (ResidentDoesNotExistException ex) {
            return ResponseEntity.status(404).body(new ApiResponse(ex.getMessage(), false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllResidents() {
        try {
            List<OnboardResidentResponse> responses = residentService.getAllResidents()
                    .stream()
                    .map(mapper::toOnboardResidentResponse)
                    .toList();
            return ResponseEntity.ok(new ApiResponse("Residents fetched successfully", true, responses));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateResidentStatus(@PathVariable String id, @RequestParam boolean isEnabled) {
        try {
            Resident resident = residentService.updateResidentStatus(id, isEnabled);
            return ResponseEntity.ok(new ApiResponse("Resident status updated successfully", true, mapper.toOnboardResidentResponse(resident)));
        } catch (ResidentDoesNotExistException ex) {
            return ResponseEntity.status(404).body(new ApiResponse(ex.getMessage(), false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/by-phone")
    public ResponseEntity<ApiResponse> getResidentByPhoneNumber(@RequestParam String phoneNumber) {
        try {
            Resident resident = residentService.findResidentByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(new ApiResponse("Resident fetched successfully", true, mapper.toOnboardResidentResponse(resident)));
        } catch (ResidentDoesNotExistException ex) {
            return ResponseEntity.status(404).body(new ApiResponse(ex.getMessage(), false, null));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }
}
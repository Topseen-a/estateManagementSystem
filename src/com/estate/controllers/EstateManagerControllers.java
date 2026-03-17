package com.estate.controllers;

import com.estate.data.models.GatePass;
import com.estate.data.models.Resident;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.estate.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.estate.services.MonitoringService;
import com.estate.services.ResidentManagementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estate")
public class EstateManagerControllers {

    private final ResidentManagementService residentManagementService;
    private final MonitoringService monitoringService;

    @Autowired
    public EstateManagerControllers(ResidentManagementService residentManagementService, MonitoringService monitoringService) {
        this.residentManagementService = residentManagementService;
        this.monitoringService = monitoringService;
    }

    @GetMapping("/residents")
    public List<Resident> getAllResidents() {
        return residentManagementService.getAllResidents();
    }

    @GetMapping("/residents/phone")
    public Resident getResidentByPhone(@RequestParam String phoneNumber) {
        return residentManagementService.findResidentByPhoneNumber(phoneNumber);
    }

    @PostMapping("/residents")
    public OnboardResidentResponse registerResident(@RequestBody Resident resident) {
        Resident savedResident = residentManagementService.registerResident(resident);
        return new Mapper().toOnboardResidentResponse(savedResident);
    }

    @PutMapping("/residents/{id}")
    public OnboardResidentResponse updateResidentStatus(@PathVariable String id, @RequestParam boolean isEnabled) {
        Resident updatedResident = residentManagementService.updateResidentStatus(id, isEnabled);
        return new Mapper().toOnboardResidentResponse(updatedResident);
    }

    @GetMapping("/statistics")
    public Map<String, Object> getEstateStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalResidents", monitoringService.getTotalResidents());
        statistics.put("totalGatePasses", monitoringService.getTotalGates());
        return statistics;
    }

    @GetMapping("/gatepasses")
    public List<GenerateResidentEntryCodeResponse> getAllGatePasses() {
        List<GatePass> gatePasses = monitoringService.getAllGatePasses();
        return gatePasses.stream()
                .map(new Mapper()::toGenerateResidentEntryCodeResponse)
                .toList();
    }

    @GetMapping("/gatepasses/resident/{residentId}")
    public List<GenerateResidentEntryCodeResponse> getGatePassesForResident(@PathVariable String residentId) {
        List<GatePass> gatePasses = monitoringService.getGatePassesForResident(residentId);
        return gatePasses.stream()
                .map(new Mapper()::toGenerateResidentEntryCodeResponse)
                .toList();
    }

    @GetMapping("/gatepasses/expired/{code}")
    public boolean isGatePassExpired(@PathVariable String code) {
        return monitoringService.isGatePassExpired(code);
    }
}
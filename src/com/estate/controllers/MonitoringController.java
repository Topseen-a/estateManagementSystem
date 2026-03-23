package com.estate.controllers;

import com.estate.data.models.GatePass;
import com.estate.dtos.responses.ApiResponse;
import com.estate.services.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping("/total-residents")
    public ResponseEntity<ApiResponse> getTotalResidents() {
        try {
            Long total = monitoringService.getTotalResidents();
            return ResponseEntity.ok(new ApiResponse("Total residents fetched successfully", true, total));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/total-gate-passes")
    public ResponseEntity<ApiResponse> getTotalGatePasses() {
        try {
            Long total = monitoringService.getTotalGatePasses();
            return ResponseEntity.ok(new ApiResponse("Total gate passes fetched successfully", true, total));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/all-gate-passes")
    public ResponseEntity<ApiResponse> getAllGatePasses() {
        try {
            List<GatePass> passes = monitoringService.getAllGatePasses();
            return ResponseEntity.ok(new ApiResponse("All gate passes fetched successfully", true, passes));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<ApiResponse> getGatePassesForResident(@PathVariable String residentId) {
        try {
            List<GatePass> passes = monitoringService.getGatePassesForResident(residentId);
            return ResponseEntity.ok(new ApiResponse("Gate passes for resident fetched successfully", true, passes));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/check-expired")
    public ResponseEntity<ApiResponse> isGatePassExpired(@RequestParam String code) {
        try {
            Boolean expired = monitoringService.isGatePassExpired(code);
            return ResponseEntity.ok(new ApiResponse("Gate pass expiration status fetched successfully", true, expired));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }
}
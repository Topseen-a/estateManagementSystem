package com.estate.controllers;

import com.estate.data.models.GatePass;
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
    public ResponseEntity<Long> getTotalResidents() {
        return ResponseEntity.ok(monitoringService.getTotalResidents());
    }

    @GetMapping("/total-gate-passes")
    public ResponseEntity<Long> getTotalGatePasses() {
        return ResponseEntity.ok(monitoringService.getTotalGatePasses());
    }

    @GetMapping("/all-gate-passes")
    public ResponseEntity<List<GatePass>> getAllGatePasses() {
        return ResponseEntity.ok(monitoringService.getAllGatePasses());
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<GatePass>> getGatePassesForResident(@PathVariable String residentId) {
        return ResponseEntity.ok(monitoringService.getGatePassesForResident(residentId));
    }

    @GetMapping("/check-expired")
    public ResponseEntity<Boolean> isGatePassExpired(@RequestParam String code) {
        return ResponseEntity.ok(monitoringService.isGatePassExpired(code));
    }
}
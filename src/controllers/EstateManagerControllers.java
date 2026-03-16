package controllers;

import data.models.GatePass;
import data.models.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.MonitoringService;
import services.ResidentManagementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class EstateManagerControllers {

    private final ResidentManagementService residentManagementService;
    private final MonitoringService monitoringService;

    @Autowired
    public EstateManagerControllers(ResidentManagementService residentManagementService, MonitoringService monitoringService) {
        this.residentManagementService = residentManagementService;
        this.monitoringService = monitoringService;
    }

    @GetMapping
    public List<Resident> getAllResidents() {
        return residentManagementService.getAllResidents();
    }

    @GetMapping
    public Resident getResidentByPhone(@RequestParam String phoneNumber) {
        return residentManagementService.findResidentByPhoneNumber(phoneNumber);
    }

    @PostMapping
    public Resident registerResident(@RequestBody Resident resident) {
        return residentManagementService.registerResident(resident);
    }

    @PutMapping
    public Resident updateResidentStatus(@PathVariable String id, @RequestParam boolean isEnabled) {
        return residentManagementService.updateResidentStatus(id, isEnabled);
    }

    @GetMapping
    public Map<String, Object> getEstateStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalResidents", monitoringService.getTotalResidents());
        statistics.put("totalGatePasses", monitoringService.getTotalGates());
        return statistics;
    }

    @GetMapping
    public List<GatePass> getAllGatePasses() {
        return monitoringService.getAllGatePasses();
    }

    @GetMapping
    public List<GatePass> getGatePassesForResident(@PathVariable String residentId) {
        return monitoringService.getGatePassesForResident(residentId);
    }

    @GetMapping
    public boolean isGatePassExpired(@PathVariable String code) {
        return monitoringService.isGatePassExpired(code);
    }
}

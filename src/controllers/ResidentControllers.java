package controllers;

import data.models.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ResidentManagementService;

import java.util.List;

@RestController
@RequestMapping
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

    @GetMapping
    public Resident getResidentByPhoneNumber(@RequestParam String phoneNumber) {
        return residentManagementService.findResidentByPhoneNumber(phoneNumber);
    }

    @PostMapping
    public Resident registerResident(@RequestBody Resident resident) {
        return  residentManagementService.registerResident(resident);
    }

    @PutMapping
    public Resident updateResidentStatus(@PathVariable String residentId, @RequestParam boolean isEnabled) {
        return residentManagementService.updateResidentStatus(residentId, isEnabled);
    }
}

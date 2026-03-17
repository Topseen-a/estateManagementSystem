package com.estate.controllers;

import com.estate.data.models.Visitor;
import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.estate.dtos.responses.ValidateCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.estate.services.GateAccessService;

import java.util.List;

@RestController
@RequestMapping("/api/gatepass")
public class GateManControllers {

    private final GateAccessService gateAccessService;

    @Autowired
    public GateManControllers(GateAccessService gateAccessService) {
        this.gateAccessService = gateAccessService;
    }

    @PostMapping("/generate")
    public GenerateResidentEntryCodeResponse generateGatePass(@RequestBody GenerateResidentEntryCodeRequest residentEntryCodeRequest, @RequestBody Visitor visitor) {
        return gateAccessService.generateGatePass(residentEntryCodeRequest, visitor);
    }

    @GetMapping("/validate")
    public ValidateCodeResponse validateGatePass(@RequestParam String code) {
        return gateAccessService.validateGatePass(code);
    }

    @GetMapping("/resident/{residentId}")
    public List<GenerateResidentEntryCodeResponse> getResidentPasses(@PathVariable String residentId) {
        return gateAccessService.getPassesByResident(residentId);
    }
}

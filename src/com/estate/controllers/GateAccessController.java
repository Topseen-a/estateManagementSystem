package com.estate.controllers;

import com.estate.data.models.Visitor;
import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.estate.dtos.responses.ValidateCodeResponse;
import com.estate.services.GateAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gate-access")
@RequiredArgsConstructor
public class GateAccessController {

    private final GateAccessService gateAccessService;

    @PostMapping("/generate")
    public ResponseEntity<GenerateResidentEntryCodeResponse> generateGatePass(@RequestBody GenerateResidentEntryCodeRequest request, @RequestParam(required = false) String visitorName, @RequestParam(required = false) String visitorPhoneNumber) {

        GenerateResidentEntryCodeResponse response;

        if (visitorName != null && visitorPhoneNumber != null) {
            Visitor visitor = new Visitor();
            visitor.setName(visitorName);
            visitor.setPhoneNumber(visitorPhoneNumber);
            response = gateAccessService.generateGatePass(request, visitor);
        } else {
            response = gateAccessService.generateGatePass(request, null);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateCodeResponse> validateGatePass(@RequestParam String code) {
        ValidateCodeResponse response = gateAccessService.validateGatePass(code);
        if (response == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<GenerateResidentEntryCodeResponse>> getPassesByResident(
            @PathVariable String residentId) {
        List<GenerateResidentEntryCodeResponse> passes = gateAccessService.getPassesByResident(residentId);
        return ResponseEntity.ok(passes);
    }
}
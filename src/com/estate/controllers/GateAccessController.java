package com.estate.controllers;

import com.estate.data.models.Visitor;
import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.estate.dtos.requests.ValidateCodeRequest;
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
    public ResponseEntity<GenerateResidentEntryCodeResponse> generateGatePass(@RequestBody GenerateVisitorEntryCodeRequest request) {

        Visitor visitor = new Visitor();
        visitor.setName(request.getVisitorName());
        visitor.setPhoneNumber(request.getVisitorPhoneNumber());
        visitor.setPurposeOfComing(request.getPurposeOfVisit());

        GenerateResidentEntryCodeRequest residentRequest = new GenerateResidentEntryCodeRequest();
        residentRequest.setResidentId(request.getResidentId());

        GenerateResidentEntryCodeResponse response = gateAccessService.generateGatePass(residentRequest, visitor);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateCodeResponse> validateGatePass(@RequestBody ValidateCodeRequest request) {

        ValidateCodeResponse response = gateAccessService.validateGatePass(request.getCode());

        if (response == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<GenerateResidentEntryCodeResponse>> getPassesByResident(@PathVariable String residentId) {

        List<GenerateResidentEntryCodeResponse> passes = gateAccessService.getPassesByResident(residentId);

        return ResponseEntity.ok(passes);
    }
}
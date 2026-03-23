package com.estate.controllers;

import com.estate.data.models.Visitor;
import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.estate.dtos.requests.ValidateCodeRequest;
import com.estate.dtos.responses.ApiResponse;
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
    public ResponseEntity<ApiResponse> generateGatePass(@RequestBody GenerateVisitorEntryCodeRequest request) {
        try {
            Visitor visitor = new Visitor();
            visitor.setName(request.getVisitorName());
            visitor.setPhoneNumber(request.getVisitorPhoneNumber());
            visitor.setPurposeOfComing(request.getPurposeOfVisit());

            GenerateResidentEntryCodeRequest residentRequest = new GenerateResidentEntryCodeRequest();
            residentRequest.setResidentId(request.getResidentId());

            GenerateResidentEntryCodeResponse response = gateAccessService.generateGatePass(residentRequest, visitor);

            return ResponseEntity.ok(new ApiResponse("Gate pass generated successfully", true, response));

        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<ApiResponse> validateGatePass(@RequestBody ValidateCodeRequest request) {
        try {
            ValidateCodeResponse response = gateAccessService.validateGatePass(request.getCode());

            if (response == null) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid code", false, null));
            }

            return ResponseEntity.ok(new ApiResponse("Gate pass validated successfully", true, response));

        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<ApiResponse> getPassesByResident(@PathVariable String residentId) {
        try {
            List<GenerateResidentEntryCodeResponse> passes = gateAccessService.getPassesByResident(residentId);

            return ResponseEntity.ok(new ApiResponse("Gate passes fetched successfully", true, passes));

        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ApiResponse("Something went wrong: " + ex.getMessage(), false, null));
        }
    }
}
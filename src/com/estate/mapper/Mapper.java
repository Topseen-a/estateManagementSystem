package com.estate.mapper;

import com.estate.data.models.GatePass;
import com.estate.data.models.Resident;
import com.estate.data.models.Visitor;
import com.estate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.estate.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.dtos.responses.ValidateCodeResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public OnboardResidentResponse toOnboardResidentResponse(Resident resident) {
        OnboardResidentResponse onboardResidentResponse = new OnboardResidentResponse();
        onboardResidentResponse.setResidentId(resident.getId());
        onboardResidentResponse.setResidentName(resident.getName());
        onboardResidentResponse.setDateRegistered(resident.getDateRegistered().toString());

        return onboardResidentResponse;
    }

    public GenerateResidentEntryCodeResponse toGenerateResidentEntryCodeResponse(GatePass  gatePass) {
        GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse = new GenerateResidentEntryCodeResponse();
        generateResidentEntryCodeResponse.setCode(gatePass.getCode());
        generateResidentEntryCodeResponse.setResidentName(gatePass.getResidentId());
        generateResidentEntryCodeResponse.setCodeType(gatePass.getPassType().name());
        generateResidentEntryCodeResponse.setValidTill(gatePass.getValidTill().toString());

        return generateResidentEntryCodeResponse;
    }

    public GenerateVisitorEntryCodeResponse toGenerateVisitorEntryCodeResponse(Visitor visitor) {
        GenerateVisitorEntryCodeResponse generateVisitorEntryCodeResponse = new GenerateVisitorEntryCodeResponse();
        generateVisitorEntryCodeResponse.setVisitorName(visitor.getName());
        generateVisitorEntryCodeResponse.setCodeType("VISITOR");

        return generateVisitorEntryCodeResponse;
    }

    public ValidateCodeResponse  toValidateCodeResponse(GatePass gatePass, Resident resident, Visitor visitor) {
        ValidateCodeResponse validateCodeResponse = new ValidateCodeResponse();

        if (resident != null) validateCodeResponse.setResidentName(resident.getName());
        if (visitor != null) validateCodeResponse.setVisitorsName(visitor.getName());
        validateCodeResponse.setCodeType(gatePass.getPassType().name());
        if (resident != null) validateCodeResponse.setCreatedBy(resident.getName());
        else validateCodeResponse.setCreatedBy("SYSTEM");
        validateCodeResponse.setValid(gatePass.isValid());

        return validateCodeResponse;
    }
}

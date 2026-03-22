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
        if (resident == null) return null;

        OnboardResidentResponse response = new OnboardResidentResponse();
        response.setResidentId(resident.getId());
        response.setResidentName(resident.getName());

        if (resident.getDateRegistered() != null) {
            response.setDateRegistered(resident.getDateRegistered().toString());
        } else {
            response.setDateRegistered(null);
        }

        return response;
    }

    public GenerateResidentEntryCodeResponse toGenerateResidentEntryCodeResponse(GatePass gatePass, Resident resident) {
        if (gatePass == null) return null;

        GenerateResidentEntryCodeResponse response = new GenerateResidentEntryCodeResponse();
        response.setCode(gatePass.getCode());

        if (resident != null) {
            response.setResidentName(resident.getName());
        } else {
            response.setResidentName(gatePass.getResidentId());
        }

        if (gatePass.getPassType() != null) {
            response.setCodeType(gatePass.getPassType().name());
        } else {
            response.setCodeType(null);
        }

        if (gatePass.getValidTill() != null) {
            response.setValidTill(gatePass.getValidTill().toString());
        } else {
            response.setValidTill(null);
        }

        return response;
    }

    public GenerateVisitorEntryCodeResponse toGenerateVisitorEntryCodeResponse(GatePass gatePass, Visitor visitor) {
        if (visitor == null || gatePass == null) return null;

        GenerateVisitorEntryCodeResponse response = new GenerateVisitorEntryCodeResponse();
        response.setVisitorName(visitor.getName());
        response.setCode(gatePass.getCode());
        response.setCodeType("VISITOR");

        if (gatePass.getValidTill() != null) {
            response.setValidTill(gatePass.getValidTill().toString());
        } else {
            response.setValidTill(null);
        }

        return response;
    }

    public ValidateCodeResponse toValidateCodeResponse(GatePass gatePass, Resident resident, Visitor visitor) {
        if (gatePass == null) return null;

        ValidateCodeResponse response = new ValidateCodeResponse();

        if (resident != null) {
            response.setResidentName(resident.getName());
            response.setCreatedBy(resident.getName());
        } else {
            response.setResidentName(null);
            response.setCreatedBy("SYSTEM");
        }

        if (visitor != null) {
            response.setVisitorName(visitor.getName());
        } else {
            response.setVisitorName(null);
        }

        if (gatePass.getPassType() != null) {
            response.setCodeType(gatePass.getPassType().name());
        } else {
            response.setCodeType(null);
        }

        response.setValid(gatePass.isValid());

        return response;
    }
}

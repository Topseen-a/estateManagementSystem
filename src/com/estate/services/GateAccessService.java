package com.estate.services;

import com.estate.data.models.GatePass;
import com.estate.data.models.Resident;
import com.estate.data.models.Type;
import com.estate.data.models.Visitor;
import com.estate.data.repositories.GatePassRepository;
import com.estate.data.repositories.ResidentRepository;
import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.estate.dtos.responses.ValidateCodeResponse;
import com.estate.exceptions.InvalidGatePassException;
import com.estate.exceptions.ResidentDisabledException;
import com.estate.exceptions.ResidentDoesNotExistException;
import lombok.RequiredArgsConstructor;
import com.estate.mapper.Mapper;
import com.estate.notification.NotificationManager;
import org.springframework.stereotype.Service;
import com.estate.utils.RandomCodeGenerator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GateAccessService {

    private final ResidentRepository residentRepository;
    private final GatePassRepository gatePassRepository;
    private final RandomCodeGenerator  randomCodeGenerator;
    private final NotificationManager notificationManager;
    private final Mapper mapper;

    public GenerateResidentEntryCodeResponse generateGatePass(GenerateResidentEntryCodeRequest residentEntryCodeRequest, Visitor visitor) {
        Resident resident = residentRepository.findById(residentEntryCodeRequest.getResidentId())
                .orElseThrow(() -> new ResidentDoesNotExistException("Resident not found"));

        if (!resident.isEnabled()) {
            throw new ResidentDisabledException("Resident account is disabled");
        }

        String code = randomCodeGenerator.generateUniqueCode();

        GatePass gatePass = new GatePass();
        gatePass.setCode(code);
        gatePass.setVisitor(visitor);
        gatePass.setResidentId(resident.getId());
        gatePass.setValidTill(residentEntryCodeRequest.getValidTill());
        gatePass.setPassType(Type.ENTRY);
        gatePass.setValid(true);

        notificationManager.notifyByEmailAndSms(resident.getEmail(), resident.getPhoneNumber(), "Your gate pass code is " +  code);

        return mapper.toGenerateResidentEntryCodeResponse(gatePass, resident);
    }

    public ValidateCodeResponse validateGatePass(String code) {
        GatePass gatePass = gatePassRepository.findByCode(code)
                .orElseThrow(() -> new InvalidGatePassException("Invalid Gate pass"));

        if (gatePass.getValidTill() != null && gatePass.getValidTill().isBefore(LocalTime.from(LocalDateTime.now()))) {
            gatePass.setValid(false);
            gatePassRepository.save(gatePass);
            return null;
        }

        Resident resident = residentRepository.findById(gatePass.getResidentId())
                .orElseThrow(() -> new ResidentDoesNotExistException("Resident not found"));

        Visitor visitor = gatePass.getVisitor();

        return mapper.toValidateCodeResponse(gatePass, resident, visitor);
    }

    public List<GenerateResidentEntryCodeResponse> getPassesByResident(String residentId) {
        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new ResidentDoesNotExistException("Resident not found"));

        return gatePassRepository.findAll()
                .stream()
                .filter(gp -> residentId.equals(gp.getResidentId()))
                .map(gp -> mapper.toGenerateResidentEntryCodeResponse(gp, resident))
                .toList();
    }
}

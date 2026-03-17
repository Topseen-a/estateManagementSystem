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
import com.estate.exceptions.ResidentDoesNotExistException;
import lombok.RequiredArgsConstructor;
import com.estate.mapper.Mapper;
import com.estate.notification.NotificationManager;
import org.springframework.stereotype.Service;
import com.estate.utils.RandomCodeGenerator;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


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

        String code = randomCodeGenerator.generateUniqueCode();

        GatePass gatePass = new GatePass();
        gatePass.setCode(code);
        gatePass.setVisitor(visitor);
        gatePass.setResidentId(resident.getId());
        gatePass.setValidTill(residentEntryCodeRequest.getValidTill());
        gatePass.setPassType(Type.ENTRY);
        gatePass.setValid(true);

        notificationManager.notifyByEmailAndSms(resident.getEmail(), resident.getPhoneNumber(), "Your gate pass code is " +  code);

        return mapper.toGenerateResidentEntryCodeResponse(gatePass);
    }

    public ValidateCodeResponse validateGatePass(String code) {
        Optional<GatePass> gatePassOption = gatePassRepository.findByCodeAndIsValid(code, true);

        if (gatePassOption.isEmpty()) return null;

        GatePass gatePass = gatePassOption.get();

        if (gatePass.getValidTill().isBefore(LocalTime.now())) {
            gatePass.setValid(false);
            gatePassRepository.save(gatePass);
            return null;
        }

        Resident resident = residentRepository.findById(gatePass.getResidentId()).orElseThrow(() -> new ResidentDoesNotExistException("Resident not found"));
        Visitor visitor = null;

        return mapper.toValidateCodeResponse(gatePass, resident, visitor);
    }

    public List<GenerateResidentEntryCodeResponse> getPassesByResident(String residentId) {
        List<GatePass> gatePasses = gatePassRepository.findByResidentId(residentId);
        return gatePasses.stream()
                .map(mapper::toGenerateResidentEntryCodeResponse)
                .toList();
    }
}

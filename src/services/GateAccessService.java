package services;

import data.models.GatePass;
import data.models.Resident;
import data.models.Type;
import data.models.Visitor;
import data.repositories.GatePassRepository;
import data.repositories.ResidentRepository;
import dtos.requests.GenerateResidentEntryCodeRequest;
import dtos.responses.GenerateResidentEntryCodeResponse;
import dtos.responses.ValidateCodeResponse;
import exceptions.ResidentDoesNotExistException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import notification.NotificationManager;
import org.springframework.stereotype.Service;
import utils.RandomCodeGenerator;

import java.time.LocalTime;
import java.util.ArrayList;
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

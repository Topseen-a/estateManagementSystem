package com.estate.services;

import com.estate.data.models.GatePass;
import com.estate.data.repositories.GatePassRepository;
import com.estate.data.repositories.ResidentRepository;
import com.estate.exceptions.GatePassDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final GatePassRepository gatePassRepository;
    private final ResidentRepository residentRepository;

    public long getTotalResidents() {
        return residentRepository.count();
    }

    public long getTotalGates() {
        return gatePassRepository.count();
    }

    public List<GatePass> getAllGatePasses() {
        return gatePassRepository.findAll();
    }

    public List<GatePass> getGatePassesForResident(String residentId) {
        return gatePassRepository.findByResidentId(residentId);
    }

    public boolean isGatePassExpired(String code) {
        GatePass gatePass = gatePassRepository.findByCode(code)
                .orElseThrow(() -> new GatePassDoesNotExistException("GatePass code not found"));

        return gatePass.getValidTill().isBefore(LocalTime.now());
    }
}

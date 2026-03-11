package services;

import data.repositories.GatePassRepository;
import data.repositories.ResidentRepository;
import org.springframework.stereotype.Service;


@Service
public class GateAccessService {

    private ResidentRepository residentRepository;
    private GatePassRepository gatePassRepository;
}
package services;

import data.models.GatePass;
import java.util.List;

public interface GatePassService {
    List<GatePass> getAllGatePasses();
    int countGatePasses();
    GatePass createGatePass(int residentId, int visitorId);
    GatePass findGatePassById(int id);
    GatePass updateGatePass(int id, int residentId, int visitorId);
    void deleteGatePass(int id);
}
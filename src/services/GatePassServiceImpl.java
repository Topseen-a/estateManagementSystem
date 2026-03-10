package services;

import data.models.GatePass;
import data.repositories.GatePassRepo;

import java.util.List;

public class GatePassServiceImpl implements GatePassService {

    private GatePassRepo gatePassRepo;

    public GatePassServiceImpl(GatePassRepo gatePassRepo) {
        this.gatePassRepo = gatePassRepo;
    }

    @Override
    public List<GatePass> getAllGatePasses() {
        return gatePassRepo.findAll();
    }

    @Override
    public int countGatePasses() {
        return gatePassRepo.count();
    }

    @Override
    public GatePass createGatePass(int residentId, int visitorId) {
        GatePass gatePass = new GatePass(residentId, visitorId);
        return gatePassRepo.save(gatePass);
    }

    @Override
    public GatePass findGatePassById(int id) {
        return gatePassRepo.findById(id);
    }

    @Override
    public GatePass updateGatePass(int id, int residentId, int visitorId) {

        GatePass existingGatePass = gatePassRepo.findById(id);

        if (existingGatePass == null) {
            throw new IllegalArgumentException("GatePass not found");
        }

        GatePass updatedGatePass = new GatePass(residentId, visitorId);
        updatedGatePass.setId(id);

        return gatePassRepo.save(updatedGatePass);
    }

    @Override
    public void deleteGatePass(int id) {

        GatePass gatePass = gatePassRepo.findById(id);

        if (gatePass != null) {
            gatePassRepo.delete(gatePass);
        }
    }
}
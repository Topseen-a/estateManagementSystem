package data.repositories;

import data.models.GatePass;

import java.util.ArrayList;
import java.util.List;

public class GatePasses implements GatePassRepo {

    private List<GatePass> gatePasses = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<GatePass> findAll() {
        return new ArrayList<>(gatePasses);
    }

    @Override
    public int count() {
        return gatePasses.size();
    }

    @Override
    public GatePass findById(int id) {
        for (GatePass pass : gatePasses) {
            if (pass.getId() == id) {
                return pass;
            }
        }
        return null;
    }

    @Override
    public List<GatePass> findByResidentId(int residentId) {

        List<GatePass> residentGatePasses = new ArrayList<>();

        for (GatePass pass : gatePasses) {
            if (pass.getResidentId() == residentId) {
                residentGatePasses.add(pass);
            }
        }
        return residentGatePasses;
    }

    @Override
    public GatePass save(GatePass pass) {

        if (pass == null) {
            throw new IllegalArgumentException("GatePass cannot be null");
        }

        if (pass.getId() == 0) {
            pass.setId(nextId++);
            gatePasses.add(pass);
        } else {

            GatePass existingPass = findById(pass.getId());

            if (existingPass != null) {
                int index = gatePasses.indexOf(existingPass);
                gatePasses.set(index, pass);
            } else {
                gatePasses.add(pass);
            }
        }
        return pass;
    }

    @Override
    public void delete(GatePass pass) {
        if (pass != null) {
            gatePasses.remove(pass);
        }
    }

    @Override
    public void deleteById(int id) {

        GatePass pass = findById(id);

        if (pass != null) {
            gatePasses.remove(pass);
        }
    }

    @Override
    public void deleteAll() {
        gatePasses.clear();
    }
}
package data.repositories;

import data.models.Resident;

import java.util.ArrayList;
import java.util.List;

public class Residents implements ResidentRepo {

    private List<Resident> residents = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Resident> findAll() {
        return new ArrayList<>(residents);
    }

    @Override
    public int count() {
        return residents.size();
    }

    @Override
    public Resident findById(int id) {
        for (Resident resident : residents) {
            if (resident.getId() == id) {
                return resident;
            }
        }
        return null;
    }

    @Override
    public Resident save(Resident resident) {

        if (resident == null) {
            throw new IllegalArgumentException("Resident cannot be null");
        }

        if (resident.getId() == 0) {
            resident.setId(nextId++);
            residents.add(resident);
        } else {

            Resident existingResident = findById(resident.getId());

            if (existingResident != null) {
                int index = residents.indexOf(existingResident);
                residents.set(index, resident);
            } else {
                residents.add(resident);
            }
        }
        return resident;
    }

    @Override
    public void delete(Resident resident) {
        if (resident != null) {
            residents.remove(resident);
        }
    }

    @Override
    public void deleteById(int id) {

        Resident resident = findById(id);

        if (resident != null) {
            residents.remove(resident);
        }
    }

    @Override
    public void deleteAll() {
        residents.clear();
    }
}
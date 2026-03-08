package services;

import data.models.Resident;
import data.repositories.ResidentRepo;

import java.util.List;

public class ResidentService {

    private final ResidentRepo residentRepo;

    public ResidentService(ResidentRepo residentRepo) {
        this.residentRepo = residentRepo;
    }

    public List<Resident> getAllResidents() {
        return residentRepo.findAll();
    }

    public int countResidents() {
        return residentRepo.count();
    }

    public Resident createResident(String name, String phoneNumber, String houseAddress) {
        Resident resident = new Resident(name, phoneNumber, houseAddress);
        return residentRepo.save(resident);
    }

    public Resident findResidentById(int id) {
        return residentRepo.findById(id);
    }

    public Resident updateResident(int id, String name, String phoneNumber, String address) {
        Resident existingResident = residentRepo.findById(id);

        if (existingResident == null) {
            throw new IllegalArgumentException("Resident not found");
        }
        Resident updatedResident = new Resident(name, phoneNumber, address);
        updatedResident.setId(id);

        return residentRepo.save(updatedResident);
    }

    public void deleteResident(int id) {
        Resident resident = residentRepo.findById(id);

        if (resident != null) {
            residentRepo.delete(resident);
        }
    }
}

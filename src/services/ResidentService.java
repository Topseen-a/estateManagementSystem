package services;

import data.models.Resident;
import data.repositories.ResidentRepo;

public class ResidentService {

    private final ResidentRepo residentRepo;

    public ResidentService(ResidentRepo residentRepo) {
        this.residentRepo = residentRepo;
    }

    public Resident createResident(String name, String phoneNumber, String houseAddress) {
        Resident resident = new Resident(name, phoneNumber, houseAddress);
        return residentRepo.save(resident);
    }

    public int countResidents() {
        return residentRepo.count();
    }
}

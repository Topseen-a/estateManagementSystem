package services;

import data.models.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getAllResidents();
    int countResidents();
    Resident createResident(String name, String phoneNumber, String houseAddress);
    Resident findResidentById(int id);
    Resident updateResident(int id, String name, String phoneNumber, String address);
    void deleteResident(int id);
}
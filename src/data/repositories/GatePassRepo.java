package data.repositories;

import data.models.GatePass;
import java.util.List;

public interface GatePassRepo {
    List<GatePass> findAll();
    GatePass findById(int id);
    List<GatePass> findByResidentId(int residentId);
    GatePass save(GatePass pass);
    void delete(GatePass pass);
    void deleteById(int id);
    void deleteAll();
    int count();
}

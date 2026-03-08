package data.repositories;

import data.models.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ResidentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentServiceTest {

    private ResidentService service;

    @BeforeEach
    public void setUp() {
        service = new ResidentService(new Residents());
    }

    @Test
    public void testThatResidentCanBeCreated() {
        Resident resident = service.createResident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        assertEquals(1,resident.getId());
        assertEquals(1, service.countResidents());
        assertEquals("Tayo Ade", resident.getName());
    }


}

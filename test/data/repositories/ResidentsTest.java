package data.repositories;

import data.models.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResidentsTest {
    private Residents residents;
    private Resident residentOne;
    private Resident residentTwo;

    @BeforeEach
    public void setUp() {
        residents = new Residents();

        residentOne = new Resident();
        residentOne.setName("Tayo Ade");
        residentOne.setPhoneNumber("08149587217");
        residentOne.setHouseAddress("Block A, Flat 5");

        residentTwo = new Resident();
        residentTwo.setName("Tolu Folusho");
        residentTwo.setPhoneNumber("08033297106");
        residentTwo.setHouseAddress("Block B, Flat 10");
    }

    @Test
    public void testSaveResident() {
        Resident saved = residents.save(residentOne);
        assertEquals(1, residents.count());
        assertEquals(1, saved.getId());
        assertNotNull(saved);
    }

    @Test
    public void testSaveMultipleResidents() {
        residents.save(residentOne);
        residents.save(residentTwo);
        assertEquals(2, residents.count());
        assertEquals(1, residentOne.getId());
        assertEquals(2, residentTwo.getId());
    }

    @Test
    public void testFindById() {
        residents.save(residentOne);
        Resident found = residents.findById(1);
        assertNotNull(found);
        assertEquals("Tayo Ade", found.getName());
        assertEquals("Block A, Flat 5", found.getHouseAddress());
    }

    @Test
    public void testFindByIdNotFound() {
        Resident found = residents.findById(999);
        assertNull(found);
    }

    @Test
    public void testFindAll() {
        residents.save(residentOne);
        residents.save(residentTwo);
        assertEquals(2, residents.findAll().size());
    }

    @Test
    public void testFindAllEmpty() {
        assertEquals(0, residents.findAll().size());
    }

    @Test
    public void testDelete() {
        residents.save(residentOne);
        residents.save(residentTwo);
        residents.delete(residentOne);
        assertEquals(1, residents.count());
        assertNull(residents.findById(1));
    }

    @Test
    void testDeleteById() {
        residents.save(residentOne);
        residents.save(residentTwo);
        residents.deleteById(1);
        assertEquals(1, residents.count());
        assertNull(residents.findById(1));
    }

    @Test
    void testDeleteByObject() {
        residents.save(residentOne);
        residents.deleteByObject(residentTwo);
        assertEquals(1, residents.count());
    }

    @Test
    public void testDeleteAll() {
        residents.save(residentOne);
        residents.save(residentTwo);
        residents.deleteAll();
        assertEquals(0, residents.count());
    }

    @Test
    public void testUpdateResident() {
        residents.save(residentOne);
        residentOne.setName("Tolu Folusho");
        residentOne.setHouseAddress("Block C, Flat 15");
        residents.save(residentOne);

        Resident found = residents.findById(1);
        assertEquals("Tolu Folusho", found.getName());
        assertEquals("Block C, Flat 15", found.getHouseAddress());
        assertEquals(1, residents.count());
    }

    @Test
    public void testIdAutoIncrement() {
        residents.save(residentOne);
        residents.save(residentTwo);
        residents.deleteById(1);

        Resident residentThree = new Resident();
        residentThree.setName("Bayo Remi");
        residents.save(residentThree);

        assertEquals(3, residentThree.getId());
    }
}
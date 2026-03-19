package com.estate.data.repositories;

import com.estate.data.models.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ResidentsTest {

    @Autowired
    private ResidentRepository residents;


    @BeforeEach
    public void setUp() {



    }

    @Test
    public void testThatRepositoryIsEmptyInitially() {
        assertTrue(residents.findAll().isEmpty());
        assertEquals(0, residents.count());
    }

    @Test
    public void testThatResidentRepoSaves() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        residents.save(residentOne);

        assertEquals(1, residents.count());
        assertEquals(1, residentOne.getId());
    }

    @Test
    public void testThatResidentRepoSavesMultipleResidents() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident residentTwo = new Resident("Bolu Folusho", "08033297106", "Ilaje Bariga, Lagos");
        residents.save(residentOne);
        residents.save(residentTwo);

        assertEquals(2, residents.count());
        assertEquals(1, residentOne.getId());
        assertEquals(2, residentTwo.getId());
    }

    @Test
    public void testThatFindByIdReturnsCorrectResident() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        residents.save(residentOne);

        residents.findById("1");

        assertEquals("Tayo Ade", residentOne.getName());
        assertEquals("08149587217", residentOne.getPhoneNumber());
        assertEquals("Sabo Yaba, Lagos", residentOne.getHouseAddress());
    }

    @Test
    public void testThatFindByIdWithNoResidentCountIsZero() {
        assertTrue(residents.findAll().isEmpty());

        residents.findById("99");
        assertEquals(0, residents.count());
    }

    @Test
    public void testThatFindAllReturnsAllResidents() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident residentTwo = new Resident("Bolu Folusho", "08033297106", "Ilaje Bariga, Lagos");
        residents.save(residentOne);
        residents.save(residentTwo);

        assertEquals(2, residents.findAll().size());
    }

    @Test
    public void testThatDeleteRemovesResident() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident residentTwo = new Resident("Bolu Folusho", "08033297106", "Ilaje Bariga, Lagos");
        residents.save(residentOne);
        residents.save(residentTwo);

        residents.delete(residentOne);

        assertEquals(1, residents.count());
    }

    @Test
    public void testThatDeleteByIdRemovesCorrectResident() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident residentTwo = new Resident("Bolu Folusho", "08033297106", "Ilaje Bariga, Lagos");
        residents.save(residentOne);
        residents.save(residentTwo);

        residents.deleteById("1");

        assertEquals(1, residents.count());
    }

    @Test
    public void testThatDeleteAllRemovesAllResidents() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident residentTwo = new Resident("Bolu Folusho", "08033297106", "Ilaje Bariga, Lagos");
        residents.save(residentOne);
        residents.save(residentTwo);

        residents.deleteAll();

        assertTrue(residents.findAll().isEmpty());
        assertEquals(0, residents.count());
    }

    @Test
    public void testThatExistingResidentCanBeUpdated() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        residents.save(residentOne);

        Resident updatedResident = new Resident("Shina Dada", "08149587217", "Alagomeji Yaba, Lagos");
        updatedResident.setId(residentOne.getId());

        residents.save(updatedResident);

        Optional<Resident> found  = residents.findById("1");
        if (found.isPresent()) {
            found.get();
        }

        assertEquals("Shina Dada", found.getName());
        assertEquals("Alagomeji Yaba, Lagos", found.getHouseAddress());
        assertEquals(1, residents.count());
    }

    @Test
    public void testThatResidentIdIncreasesAutomatically() {
        assertTrue(residents.findAll().isEmpty());

        Resident residentOne = new Resident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident residentTwo = new Resident("Bolu Folusho", "08033297106", "Ilaje Bariga, Lagos");
        residents.save(residentOne);
        residents.save(residentTwo);

        residents.deleteById("1");

        Resident residentThree = new Resident("Bayo Remi", "09012345678", "Agege Lagos");
        residents.save(residentThree);

        assertEquals(3, residentThree.getId());
    }
}
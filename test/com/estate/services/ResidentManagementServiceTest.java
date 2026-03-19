package com.estate.services;

import com.estate.data.models.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResidentManagementServiceTest {

    private ResidentManagementService residentService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testThatResidentServiceListIsEmptyInitially() {
        assertTrue(residentService.getAllResidents().isEmpty());
        assertEquals(0, residentService.countResidents());
    }

    @Test
    public void testThatResidentCanBeCreated() {
        assertTrue(residentService.getAllResidents().isEmpty());

        Resident resident = residentService.createResident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        assertEquals(1,resident.getId());
        assertEquals(1, residentService.countResidents());
        assertEquals("Tayo Ade", resident.getName());
    }

    @Test
    public void testThatAllMultipleResidentsCanBeCreated() {
        assertTrue(residentService.getAllResidents().isEmpty());

        residentService.createResident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        residentService.createResident("Tolu Folusho", "08033297106", "Alagomeji Yaba, Lagos");
        assertEquals(2, residentService.countResidents());
    }

    @Test
    public void testThatFindResidentByIdReturnsResident() {
        assertTrue(residentService.getAllResidents().isEmpty());

        Resident resident = residentService.createResident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");

        Resident foundResident = residentService.findResidentById(resident.getId());

        assertEquals("Tayo Ade", foundResident.getName());
        assertEquals("08149587217", foundResident.getPhoneNumber());
    }

    @Test
    public void testThatResidentCanBeUpdated() {
        assertTrue(residentService.getAllResidents().isEmpty());

        Resident resident = residentService.createResident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");
        Resident updatedResident = residentService.updateResident(resident.getId(), "Ajayi Deborah", "09032277492", "Ipaja, Lagos");

        assertEquals("Ajayi Deborah", updatedResident.getName());
        assertEquals(1, residentService.countResidents());
    }

    @Test
    public void testThatResidentCanBeDeleted() {
        assertTrue(residentService.getAllResidents().isEmpty());

        Resident resident = residentService.createResident("Tayo Ade", "08149587217", "Sabo Yaba, Lagos");

        assertEquals(1, residentService.countResidents());
        residentService.deleteResident(resident.getId());

        assertEquals(0, residentService.countResidents());
    }
}

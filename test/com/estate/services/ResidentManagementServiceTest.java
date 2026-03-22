package com.estate.services;

import com.estate.data.models.Resident;
import com.estate.data.repositories.ResidentRepository;
import com.estate.dtos.requests.OnboardResidentRequest;
import com.estate.dtos.responses.OnboardResidentResponse;
import com.estate.exceptions.ResidentAlreadyRegisteredException;
import com.estate.exceptions.ResidentDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResidentManagementServiceTest {

    @Autowired
    private ResidentManagementService residentService;

    @Autowired
    private ResidentRepository residentRepository;

    @BeforeEach
    public void setUp() {
        residentRepository.deleteAll();
    }

    @Test
    public void testThatResidentCanBeOnboarded() {
        OnboardResidentRequest request = new OnboardResidentRequest();
        request.setName("Oluwaseun");
        request.setPhoneNumber("08012345678");
        request.setEmail("oluwaseun@gmail.com");

        OnboardResidentResponse response = residentService.registerResident(request);

        assertNotNull(response.getResidentId());
        assertEquals("Oluwaseun", response.getResidentName());
    }

    @Test
    public void testThatMultipleResidentsCanBeOnboarded() {
        OnboardResidentRequest requestOne = new OnboardResidentRequest();
        requestOne.setName("Oluwaseun");
        requestOne.setPhoneNumber("08012345678");
        requestOne.setEmail("oluwaseun@gmail.com");
        residentService.registerResident(requestOne);

        OnboardResidentRequest requestTwo = new OnboardResidentRequest();
        requestTwo.setName("Toluwani");
        requestTwo.setPhoneNumber("08118927456");
        requestTwo.setEmail("toluwani@gmail.com");
        residentService.registerResident(requestTwo);

        List<Resident> allResidents = residentService.getAllResidents();

        assertNotNull(allResidents);
        assertEquals(2, allResidents.size());
    }

    @Test
    public void testThatOnboardSameResidentTwiceThrowsAnError() {
        OnboardResidentRequest request = new OnboardResidentRequest();
        request.setName("Oluwaseun");
        request.setPhoneNumber("08012345678");
        request.setEmail("oluwaseun@gmail.com");

        residentService.registerResident(request);

        OnboardResidentRequest duplicate = new OnboardResidentRequest();
        duplicate.setName("Oluwaseun");
        duplicate.setPhoneNumber("08012345678");
        duplicate.setEmail("oluwaseun@gmail.com");

        assertThrows(ResidentAlreadyRegisteredException.class, () -> residentService.registerResident(duplicate));
    }

    @Test
    public void testThatFindResidentByIdReturnsResident() {
        OnboardResidentRequest request = new OnboardResidentRequest();
        request.setName("Oluwaseun");
        request.setPhoneNumber("08012345678");
        request.setEmail("oluwaseun@gmail.com");

        OnboardResidentResponse response = residentService.registerResident(request);

        Resident found = residentService.findResidentById(response.getResidentId());
        assertEquals("Oluwaseun", found.getName());
    }

    @Test
    public void testThatFindResidentByIdThatDoesNotExistThrowsAnError() {
        assertThrows(ResidentDoesNotExistException.class, () -> residentService.findResidentById("Resident not found"));
    }

    @Test
    public void testThatGetAllResidentsReturnsAllResidents() {
        OnboardResidentRequest requestOne = new OnboardResidentRequest();
        requestOne.setName("Oluwaseun");
        requestOne.setPhoneNumber("08012345678");
        requestOne.setEmail("oluwaseun@gmail.com");
        residentService.registerResident(requestOne);

        OnboardResidentRequest requestTwo = new OnboardResidentRequest();
        requestTwo.setName("Toluwani");
        requestTwo.setPhoneNumber("08118927456");
        requestTwo.setEmail("toluwani@gmail.com");
        residentService.registerResident(requestTwo);

        List<Resident> residents = residentService.getAllResidents();
        assertEquals(2, residents.size());
    }

    @Test
    public void testThatResidentCanBeEnabledAndDisable() {
        OnboardResidentRequest request = new OnboardResidentRequest();
        request.setName("Oluwaseun");
        request.setPhoneNumber("08012345678");
        request.setEmail("oluwaseun@gmail.com");

        OnboardResidentResponse response = residentService.registerResident(request);

        Resident disabled = residentService.updateResidentStatus(response.getResidentId(), false);
        assertFalse(disabled.isEnabled());

        Resident enabled = residentService.updateResidentStatus(response.getResidentId(), true);
        assertTrue(enabled.isEnabled());
    }

    @Test
    public void testThatResidentCanBeDeleted() {
        OnboardResidentRequest request = new OnboardResidentRequest();
        request.setName("Oluwaseun");
        request.setPhoneNumber("08012345678");
        request.setEmail("oluwaseun@gmail.com");

        OnboardResidentResponse response = residentService.registerResident(request);

        residentService.deleteResident(response.getResidentId());

        assertThrows(ResidentDoesNotExistException.class,
                () -> residentService.findResidentById(response.getResidentId()));
    }
}
package com.estate.services;

import com.estate.data.models.Resident;
import com.estate.data.models.Visitor;
import com.estate.data.repositories.GatePassRepository;
import com.estate.data.repositories.ResidentRepository;
import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.estate.dtos.responses.ValidateCodeResponse;
import com.estate.exceptions.InvalidGatePassException;
import com.estate.exceptions.ResidentDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GateAccessServiceTest {

    @Autowired
    private GateAccessService gateAccessService;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private GatePassRepository gatePassRepository;

    @BeforeEach
    public void setUp() {
        gatePassRepository.deleteAll();
        residentRepository.deleteAll();
    }

    @Test
    public void testThatResidentCanGenerateGatePass() {
        Resident resident = new Resident();
        resident.setName("Oluwaseun");
        resident.setPhoneNumber("08012345678");
        resident.setEmail("oluwaseun@gmail.com");
        resident.setEnabled(true);
        resident = residentRepository.save(resident);

        GenerateResidentEntryCodeRequest request = new GenerateResidentEntryCodeRequest();
        request.setResidentId(resident.getId());
        request.setValidTill(LocalTime.now().plusHours(2));

        Visitor visitor = new Visitor();
        visitor.setName("Tayo");
        visitor.setPhoneNumber("08147867524");

        GenerateResidentEntryCodeResponse response = gateAccessService.generateGatePass(request, visitor);

        assertNotNull(response);
        assertNotNull(response.getCode());
        assertEquals(resident.getName(), response.getResidentName());
    }

    @Test
    public void testThatGeneratingGatePassWithInvalidResidentThrowsException() {
        GenerateResidentEntryCodeRequest request = new GenerateResidentEntryCodeRequest();
        request.setResidentId("Resident not found");
        request.setValidTill(LocalTime.now().plusHours(1));

        assertThrows(ResidentDoesNotExistException.class, () -> gateAccessService.generateGatePass(request, null));
    }

    @Test
    public void testThatGeneratedGatePassCanBeValidated() {
        Resident resident = new Resident();
        resident.setName("Oluwaseun");
        resident.setPhoneNumber("08012345678");
        resident.setEmail("oluwaseun@gmail.com");
        resident.setEnabled(true);
        resident = residentRepository.save(resident);

        GenerateResidentEntryCodeRequest request = new GenerateResidentEntryCodeRequest();
        request.setResidentId(resident.getId());
        request.setValidTill(LocalTime.now().plusHours(2));

        GenerateResidentEntryCodeResponse generated = gateAccessService.generateGatePass(request, null);

        ValidateCodeResponse response = gateAccessService.validateGatePass(generated.getCode());

        assertNotNull(response);
        assertTrue(response.isValid());
        assertEquals(resident.getName(), response.getResidentName());
    }

    @Test
    public void testThatInvalidGatePassThrowsException() {
        assertThrows(InvalidGatePassException.class, () -> gateAccessService.validateGatePass("WRONGCODE"));
    }

    @Test
    public void testThatExpiredGatePassBecomesInvalid() {
        Resident resident = new Resident();
        resident.setName("Oluwaseun");
        resident.setPhoneNumber("08012345678");
        resident.setEmail("oluwaseun@gmail.com");
        resident.setEnabled(true);
        resident = residentRepository.save(resident);

        GenerateResidentEntryCodeRequest request = new GenerateResidentEntryCodeRequest();
        request.setResidentId(resident.getId());
        request.setValidTill(LocalTime.now().minusMinutes(1));

        GenerateResidentEntryCodeResponse generated = gateAccessService.generateGatePass(request, null);

        ValidateCodeResponse response = gateAccessService.validateGatePass(generated.getCode());

        assertNull(response);
    }
}
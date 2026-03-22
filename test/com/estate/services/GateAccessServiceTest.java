//package com.estate.services;//package services;
//
//
//import com.estate.data.models.GatePass;
//import com.estate.data.models.Resident;
//import com.estate.data.models.Type;
//import com.estate.data.models.Visitor;
//import com.estate.data.repositories.GatePassRepository;
//import com.estate.data.repositories.ResidentRepository;
//import com.estate.dtos.requests.GenerateResidentEntryCodeRequest;
//import com.estate.exceptions.ResidentDoesNotExistException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class GateAccessServiceTest {
//
//    @Autowired
//    private GateAccessService gateAccessService;
//
//    @Autowired
//    private ResidentRepository residentRepository;
//
//    @Autowired
//    private GatePassRepository gatePassRepository;
//
//    private Resident resident;
//    private Visitor visitor;
//    private GenerateResidentEntryCodeRequest request;
//
//    @BeforeEach
//    void setUp() {
//        resident = new Resident();
//        resident.setId("res1");
//        resident.setEmail("test@mail.com");
//        resident.setPhoneNumber("1234567890");
//        residentRepository.save(resident);
//
//        visitor = new Visitor();
//        visitor.setName("John Visitor");
//
//        request = new GenerateResidentEntryCodeRequest();
//        request.setResidentId("res1");
//        request.setValidTill(LocalTime.now().plusHours(2));
//    }
//
//    @Test
//    void generateGatePass_success() {
//        GatePass result = gateAccessService.generateGatePass(request, visitor);
//
//        assertNotNull(result);
//        assertEquals("ABC123", result.getCode());  // Assuming randomCodeGenerator returns "ABC123"
//        assertEquals(Type.ENTRY, result.getPassType());
//        assertTrue(result.isValid());
//
//        assertTrue(gatePassRepository.findById(result.getId()).isPresent());
//    }
//
//    @Test
//    void generateGatePass_residentNotFound_shouldThrowException() {
//        GenerateResidentEntryCodeRequest requestWithInvalidResident = new GenerateResidentEntryCodeRequest();
//        requestWithInvalidResident.setResidentId("invalidId");
//        requestWithInvalidResident.setValidTill(LocalTime.now().plusHours(2));
//
//        assertThrows(ResidentDoesNotExistException.class, () ->
//                gateAccessService.generateGatePass(requestWithInvalidResident, visitor)
//        );
//    }
//
//    @Test
//    void validateGatePass_validCode_returnsTrue() {
//        GatePass gatePass = gateAccessService.generateGatePass(request, visitor);
//
//        boolean result = gateAccessService.validateGatePass(gatePass.getCode());
//
//        assertTrue(result);
//    }
//
//    @Test
//    void validateGatePass_expiredCode_returnsFalseAndInvalidates() {
//        GatePass gatePass = gateAccessService.generateGatePass(request, visitor);
//        gatePass.setValidTill(LocalTime.now().minusHours(1));
//        gatePassRepository.save(gatePass);
//
//        boolean result = gateAccessService.validateGatePass(gatePass.getCode());
//
//        assertFalse(result);
//        assertFalse(gatePass.isValid());
//    }
//
//    @Test
//    void getPassesByResident_returnsList() {
//        gateAccessService.generateGatePass(request, visitor);
//
//        List<GatePass> result = gateAccessService.getPassesByResident("res1");
//
//        assertEquals(1, result.size());
//    }
//}
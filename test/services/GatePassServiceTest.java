package services;

import data.models.GatePass;
import data.repositories.GatePassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GatePassServiceTest {

    private GateAccessService gatePassService;

    @BeforeEach
    public void setUp() {
        gatePassService = new GateAccessService(new GatePassRepository());
    }

    @Test
    public void testThatGatePassServiceListIsEmptyInitially() {
        assertTrue(gatePassService.getAllGatePasses().isEmpty());
        assertEquals(0, gatePassService.countGatePasses());
    }

    @Test
    public void testThatGatePassCanBeCreated() {
        assertTrue(gatePassService.getAllGatePasses().isEmpty());

        GatePass gatePass = gatePassService.createGatePass(1, 1);
        assertEquals(1, gatePass.getId());
        assertEquals(1, gatePassService.countGatePasses());
        assertEquals(1, gatePass.getResidentId());
        assertEquals(1, gatePass.getVisitorId());
    }

    @Test
    public void testThatMultipleGatePassesCanBeCreated() {
        assertTrue(gatePassService.getAllGatePasses().isEmpty());

        gatePassService.createGatePass(1, 1);
        gatePassService.createGatePass(2, 2);

        assertEquals(2, gatePassService.countGatePasses());
    }

    @Test
    public void testThatFindByIdReturnsGatePass() {
        assertTrue(gatePassService.getAllGatePasses().isEmpty());

        GatePass gatePass = gatePassService.createGatePass(1, 1);
        GatePass foundGatePass = gatePassService.findGatePassById(gatePass.getId());

        assertEquals(1, foundGatePass.getResidentId());
        assertEquals(1, foundGatePass.getVisitorId());
    }

    @Test
    public void testThatGatePassCanBeUpdated() {
        assertTrue(gatePassService.getAllGatePasses().isEmpty());

        GatePass gatePass = gatePassService.createGatePass(1, 1);
        GatePass updated = gatePassService.updateGatePass(gatePass.getId(), 3, 4);

        assertEquals(3, updated.getResidentId());
        assertEquals(4, updated.getVisitorId());
        assertEquals(1, gatePassService.countGatePasses());
    }

    @Test
    public void testThatGatePassCanBeDeleted() {
        assertTrue(gatePassService.getAllGatePasses().isEmpty());

        GatePass gatePass = gatePassService.createGatePass(1, 1);

        assertEquals(1, gatePassService.countGatePasses());
        gatePassService.deleteGatePass(gatePass.getId());

        assertEquals(0, gatePassService.countGatePasses());
    }
}
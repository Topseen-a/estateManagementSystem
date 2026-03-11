package data.repositories;

import data.models.GatePass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GatePassesTest {

    private GatePassRepository gatePasses;

    @BeforeEach
    public void setUp() {
        gatePasses = new GatePassRepository();
    }

    @Test
    public void testThatGatePassesRepositoryIsEmptyAtInitial() {
        assertTrue(gatePasses.findAll().isEmpty());
        assertEquals(0, gatePasses.count());
    }

    @Test
    public void testThatGatePassRepoSaves() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        gatePasses.save(passOne);

        assertEquals(1, gatePasses.count());
        assertEquals(1, passOne.getId());
    }

    @Test
    public void testThatGatePassSavesMultipleGatePasses() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        GatePass passTwo = new GatePass(2, 2);
        gatePasses.save(passOne);
        gatePasses.save(passTwo);

        assertEquals(2, gatePasses.count());
        assertEquals(1, passOne.getId());
        assertEquals(2, passTwo.getId());
    }

    @Test
    public void testThatFindByIdReturnsCorrectGatePass() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        gatePasses.save(passOne);
        gatePasses.findById(1);

        assertEquals(1, passOne.getResidentId());
        assertEquals(1, passOne.getVisitorId());
    }

    @Test
    public void testThatFindByIdWithoutGatePassCountIsZero() {
        assertTrue(gatePasses.findAll().isEmpty());

        gatePasses.findById(999);
        assertEquals(0, gatePasses.count());
    }

    @Test
    public void testThatFindAllReturnsAllGatePasses() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        GatePass passTwo = new GatePass(2, 2);

        gatePasses.save(passOne);
        gatePasses.save(passTwo);

        assertEquals(2, gatePasses.findAll().size());
    }

    @Test
    public void testThatFindByResidentIdReturnsCorrectGatePasses() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        GatePass passTwo = new GatePass(1, 2);
        GatePass passThree = new GatePass(2, 3);

        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        gatePasses.save(passThree);

        gatePasses.findByResidentId(1);

        assertEquals(2, gatePasses.findByResidentId(1).size());
    }

    @Test
    public void testThatDeleteRemovesCorrectGatePass() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        GatePass passTwo = new GatePass(2, 2);
        gatePasses.save(passOne);
        gatePasses.save(passTwo);

        gatePasses.delete(passOne);

        assertEquals(1, gatePasses.count());
    }

    @Test
    public void testThatDeleteByIdRemovesCorrectGatePass() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        GatePass passTwo = new GatePass(2, 2);
        gatePasses.save(passOne);
        gatePasses.save(passTwo);

        gatePasses.deleteById(1);

        assertEquals(1, gatePasses.count());
    }

    @Test
    public void testThatDeleteAllRemovesAllGatePasses() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        GatePass passTwo = new GatePass(2, 2);
        gatePasses.save(passOne);
        gatePasses.save(passTwo);

        gatePasses.deleteAll();

        assertTrue(gatePasses.findAll().isEmpty());
        assertEquals(0, gatePasses.count());
    }

    @Test
    public void testThatExistingGatePassCanBeUpdated() {
        assertTrue(gatePasses.findAll().isEmpty());

        GatePass passOne = new GatePass(1, 1);
        gatePasses.save(passOne);

        GatePass updatedGatePass = new GatePass(4, 4);
        updatedGatePass.setId(passOne.getId());
        gatePasses.save(updatedGatePass);

        GatePass found = gatePasses.findById(passOne.getId());

        assertEquals(1, gatePasses.count());
        assertEquals(4, found.getResidentId());
        assertEquals(4, found.getVisitorId());
    }
}
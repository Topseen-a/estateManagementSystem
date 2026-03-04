package data.repositories;

import data.models.GatePass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GatePassesTest {
    private GatePasses gatePasses;
    private GatePass passOne;
    private GatePass passTwo;

    @BeforeEach
    public void setUp() {
        gatePasses = new GatePasses();

        passOne = new GatePass();
        passOne.setResidentId(1);
        passOne.setVisitorsId(1);
        passOne.setExpirationDate(LocalDateTime.now().plusHours(24));
        passOne.setValid(true);

        passTwo = new GatePass();
        passTwo.setResidentId(2);
        passTwo.setVisitorsId(2);
        passTwo.setExpirationDate(LocalDateTime.now().plusHours(48));
        passTwo.setValid(true);
    }

    @Test
    public void testSaveGatePass() {
        GatePass saved = gatePasses.save(passOne);
        assertEquals(1, gatePasses.count());
        assertEquals(1, saved.getId());
        assertNotNull(saved);
    }

    @Test
    public void testSaveMultipleGatePasses() {
        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        assertEquals(2, gatePasses.count());
        assertEquals(1, passOne.getId());
        assertEquals(2, passTwo.getId());
    }

    @Test
    public void testFindById() {
        gatePasses.save(passOne);
        GatePass found = gatePasses.findById(1);
        assertNotNull(found);
        assertEquals(1, found.getResidentId());
        assertEquals(1, found.getVisitorsId());
        assertTrue(found.isValid());
    }

    @Test
    public void testFindByIdNotFound() {
        GatePass found = gatePasses.findById(999);
        assertNull(found);
    }

    @Test
    public void testFindAll() {
        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        assertEquals(2, gatePasses.findAll().size());
    }

    @Test
    public void testFindAllEmpty() {
        assertEquals(0, gatePasses.findAll().size());
    }

    @Test
    public void testDelete() {
        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        gatePasses.delete(passOne);
        assertEquals(1, gatePasses.count());
        assertNull(gatePasses.findById(1));
    }

    @Test
    public void testDeleteById() {
        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        gatePasses.deleteById(1);
        assertEquals(1, gatePasses.count());
        assertNull(gatePasses.findById(1));
    }

    @Test
    public void testDeleteByObject() {
        gatePasses.save(passOne);
        gatePasses.deleteByObject(passOne);
        assertEquals(0, gatePasses.count());
    }

    @Test
    public void testDeleteAll() {
        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        gatePasses.deleteAll();
        assertEquals(0, gatePasses.count());
    }

    @Test
    public void testUpdateGatePass() {
        gatePasses.save(passOne);
        passOne.setValid(false);
        gatePasses.save(passOne);

        GatePass found = gatePasses.findById(1);
        assertFalse(found.isValid());
        assertEquals(1, gatePasses.count());
    }

    @Test
    void testGatePassCreatedAtIsSet() {
        gatePasses.save(passOne);
        GatePass found = gatePasses.findById(1);
        assertNotNull(found.getTimeCreated());
    }

    @Test
    public void testGatePassExpirationDate() {
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        passOne.setExpirationDate(expiration);
        gatePasses.save(passOne);

        GatePass found = gatePasses.findById(1);
        assertNotNull(found.getExpirationDate());
    }

    @Test
    public void testIdAutoIncrement() {
        gatePasses.save(passOne);
        gatePasses.save(passTwo);
        gatePasses.deleteById(1);

        GatePass pass3 = new GatePass();
        pass3.setResidentId(3);
        pass3.setVisitorsId(3);
        gatePasses.save(pass3);

        assertEquals(3, pass3.getId());
    }
}
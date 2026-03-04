package data.repositories;

import data.models.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorsTest {
    private Visitors visitors;
    private Visitor visitorOne;
    private Visitor visitorTwo;

    @BeforeEach
    public void setUp() {
        visitors = new Visitors();

        visitorOne = new Visitor();
        visitorOne.setName("Tayo Ade");
        visitorOne.setPurposeOfComing("Holiday");
        visitorOne.setPhoneNumber("08012345678");

        visitorTwo = new Visitor();
        visitorTwo.setName("Tolu Folusho");
        visitorTwo.setPurposeOfComing("Business meeting");
        visitorTwo.setPhoneNumber("08087654321");
    }

    @Test
    public void testSaveVisitor() {
        visitors.save(visitorOne);
        assertEquals(1, visitors.count());
        assertEquals(1, visitorOne.getId());
    }

    @Test
    public void testSaveMultipleVisitors() {
        visitors.save(visitorOne);
        visitors.save(visitorTwo);
        assertEquals(2, visitors.count());
        assertEquals(1, visitorOne.getId());
        assertEquals(2, visitorTwo.getId());
    }

    @Test
    public void testFindById() {
        visitors.save(visitorOne);
        Visitor found = visitors.findById(1);
        assertNotNull(found);
        assertEquals("Tayo Ade", found.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        Visitor found = visitors.findById(999);
        assertNull(found);
    }

    @Test
    public void testFindAll() {
        visitors.save(visitorOne);
        visitors.save(visitorTwo);
        assertEquals(2, visitors.findAll().size());
    }

    @Test
    public void testFindAllEmpty() {
        assertEquals(0, visitors.findAll().size());
    }

    @Test
    public void testDelete() {
        visitors.save(visitorOne);
        visitors.save(visitorTwo);
        visitors.delete(visitorTwo);
        assertEquals(1, visitors.count());
//        assertNull(visitors.findById(1));
    }

    @Test
    public void testDeleteById() {
        visitors.save(visitorOne);
        visitors.save(visitorTwo);
        visitors.deleteById(1);
        assertEquals(1, visitors.count());
        assertNull(visitors.findById(1));
    }

    @Test
    void testDeleteByObject() {
        visitors.save(visitorOne);
        visitors.deleteByObject(visitorOne);
        assertEquals(0, visitors.count());
    }

    @Test
    public void testDeleteAll() {
        visitors.save(visitorOne);
        visitors.save(visitorTwo);
        visitors.deleteAll();
        assertEquals(0, visitors.count());
    }

    @Test
    public void testUpdateVisitor() {
        visitors.save(visitorOne);
        visitorOne.setName("Ajayi Deborah");
        visitors.save(visitorOne);

        Visitor found = visitors.findById(1);
        assertEquals("Ajayi Deborah", found.getName());
        assertEquals(1, visitors.count());
    }
}
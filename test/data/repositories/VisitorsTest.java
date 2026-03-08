package data.repositories;

import data.models.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorsTest {

    private Visitors visitors;

    @BeforeEach
    public void setUp() {

        visitors = new Visitors();
    }

    @Test
    public void testThatRepositoryIsEmptyInitially() {
        assertTrue(visitors.findAll().isEmpty());
        assertEquals(0, visitors.count());
    }

    @Test
    public void testThatVisitorRepoSaves() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        visitors.save(visitorOne);

        assertEquals(1, visitors.count());
        assertEquals(1, visitorOne.getId());
    }

    @Test
    public void testThatVisitorRepoSavesMultipleVisitors() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        Visitor visitorTwo = new Visitor("Tolu Folusho", "Business meeting", "08087654321");
        visitors.save(visitorOne);
        visitors.save(visitorTwo);

        assertEquals(2, visitors.count());
        assertEquals(1, visitorOne.getId());
        assertEquals(2, visitorTwo.getId());
    }

    @Test
    public void testThatFindByIdReturnsCorrectVisitor() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        Visitor visitorTwo = new Visitor("Tolu Folusho", "Business meeting", "08087654321");
        visitors.save(visitorOne);
        visitors.save(visitorTwo);

        visitors.findById(1);

        assertEquals("Tayo Ade", visitorOne.getName());
        assertEquals("Holiday", visitorOne.getPurposeOfComing());
        assertEquals("08012345678", visitorOne.getPhoneNumber());
    }

    @Test
    public void testThatFindByIdWithNoVisitorCountIsZero() {
        assertTrue(visitors.findAll().isEmpty());

        visitors.findById(99);
        assertEquals(0, visitors.count());
    }

    @Test
    public void testThatFindAllReturnsAllVisitors() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        Visitor visitorTwo = new Visitor("Tolu Folusho", "Business meeting", "08087654321");
        visitors.save(visitorOne);
        visitors.save(visitorTwo);

        assertEquals(2, visitors.findAll().size());
    }

    @Test
    public void testThatDeleteRemovesVisitor() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        Visitor visitorTwo = new Visitor("Tolu Folusho", "Business meeting", "08087654321");
        visitors.save(visitorOne);
        visitors.save(visitorTwo);

        visitors.delete(visitorTwo);

        assertEquals(1, visitors.count());
    }

    @Test
    public void testThatDeleteByIdRemovesCorrectVisitor() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        Visitor visitorTwo = new Visitor("Tolu Folusho", "Business meeting", "08087654321");
        visitors.save(visitorOne);
        visitors.save(visitorTwo);

        visitors.deleteById(1);

        assertEquals(1, visitors.count());
    }

    @Test
    public void testThatDeleteAllRemovesAllVisitors() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        Visitor visitorTwo = new Visitor("Tolu Folusho", "Business meeting", "08087654321");
        visitors.save(visitorOne);
        visitors.save(visitorTwo);

        visitors.deleteAll();

        assertTrue(visitors.findAll().isEmpty());
        assertEquals(0, visitors.count());
    }

    @Test
    public void testThatExistingVisitorCanBeUpdated() {
        assertTrue(visitors.findAll().isEmpty());

        Visitor visitorOne = new Visitor("Tayo Ade", "Holiday", "08012345678");
        visitors.save(visitorOne);

        Visitor updatedVisitor = new Visitor("Ajayi Deborah", visitorOne.getPurposeOfComing(), visitorOne.getPhoneNumber());

        updatedVisitor.setId(visitorOne.getId());

        visitors.save(updatedVisitor);

        Visitor found = visitors.findById(1);

        assertEquals("Ajayi Deborah", found.getName());
        assertEquals(1, visitors.count());
    }
}
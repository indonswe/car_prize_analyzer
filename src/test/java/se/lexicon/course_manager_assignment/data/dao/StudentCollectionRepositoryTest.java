package se.lexicon.course_manager_assignment.data.dao;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here

    /*@BeforeEach
    public void setUp() {
        Student student1 = testObject.createStudent
                ("Testing1", "Testing@testing1.com", "Testingstreet");
        Student student2 = testObject.createStudent
                ("Testing2", "Testing@testing2.com", "Testingstreet");
        Student student3 = testObject.createStudent
                ("Testing3", "Testing@testing3.com", "Testingstreet");
    }*/


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }

    @Test
    void createStudent() {
        int actual = StudentSequencer.getStudentSequencer();
        int expected = actual + 1;

        Student student = testObject.createStudent
                ("Testing", "Testing@testing.com", "Testingstreet");
        actual = StudentSequencer.getStudentSequencer();

        assertEquals(expected,actual);
        assertEquals(student.getName(),"Testing");
        assertEquals(student.getEmail(),"Testing@testing.com" );
        assertEquals(student.getAddress(),"Testingstreet");
    }


    @Test
    void findByEmailIgnoreCase() {
        Student expected = testObject.createStudent
                ("Testing", "Testing@testing.com", "Testingstreet");
        Student actual = testObject.findByEmailIgnoreCase("testing@testing.com");
        assertEquals(expected,actual);

    }

    @Test
    void findByNameContains() {
        //Student expected = testObject.createStudent
               // ("Testing", "Testing@testing.com", "Testingstreet");
        //Collection<Student> actual = testObject.findByNameContains("Testing Testholm");
        //System.out.println(actual);
        //assertTrue(actual.contains(expected));

    }

    @Test
    void findById() {
        Student expected = testObject.createStudent
                ("Testing", "Testing@testing.com", "Testingstreet");
        Student actual = testObject.findById(1);

        assertEquals(expected,actual);

    }

    @Test
    void findAll() {
        Student expected = testObject.createStudent
                ("Testing", "Testing@testing.com", "Testingstreet");
        Collection<Student> actual = testObject.findAll();

        assertTrue(actual.contains(expected));

    }

    @Test
    void removeStudent() {
        Student unexpected = testObject.createStudent
                ("Testing", "Testing@testing.com", "Testingstreet");
        boolean actual = testObject.removeStudent(unexpected);
        assertEquals(actual,true);
    }

    @Test
    void clear() {
        Student expected = testObject.createStudent
                ("Testing", "Testing@testing.com", "Testingstreet");
        testObject.clear();
        Collection<Student> actual = testObject.findAll();

        assertFalse(actual.contains(expected));


    }
}

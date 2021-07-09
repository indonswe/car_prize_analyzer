package se.lexicon.course_manager_assignment.data.dao;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
    }

    @Test
    void findByNameContains() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void removeStudent() {
    }

    @Test
    void clear() {
    }
}

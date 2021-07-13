package se.lexicon.course_manager_assignment.data.service.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {

    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(studentDao);
    }

    //Write your tests here

    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }

    @Test
    void create() {
        int actual = StudentSequencer.getStudentSequencer();
        int expected = actual + 1;

        CreateStudentForm form = new CreateStudentForm
                (1,"Testing", "Testing@testing.com", "Testingstreet");

        StudentView student = testObject.create(form);

        actual = StudentSequencer.getStudentSequencer();

        assertEquals(expected,actual);
        assertEquals(student.getName(),"Testing");
        assertEquals(student.getEmail(),"Testing@testing.com" );
        assertEquals(student.getAddress(),"Testingstreet");
    }

    @Test
    void update() {
    }

    @Test
    void findById() {

        CreateStudentForm form = new CreateStudentForm
                (1,"Testing", "Testing@testing.com", "Testingstreet");

        StudentView studentView = testObject.create(form);
        StudentView actual = testObject.findById(1);

        assertEquals(studentView,actual);

    }

    @Test
    void searchByEmail() {

        CreateStudentForm form = new CreateStudentForm
                (1,"Testing", "Testing@testing.com", "Testingstreet");

        StudentView studentView = testObject.create(form);
        StudentView actual = testObject.searchByEmail("Testing@testing.com");

        assertTrue(studentView.equals(actual));

    }

    @Test
    void searchByName() {

        CreateStudentForm form = new CreateStudentForm
                (1,"Testing", "Testing@testing.com", "Testingstreet");

        StudentView studentView = testObject.create(form);
        List<StudentView> actual = testObject.searchByName("Testing");

        assertTrue(actual.contains(studentView));

    }

    @Test
    void findAll() {

        CreateStudentForm form = new CreateStudentForm
                (1,"Testing", "Testing@testing.com", "Testingstreet");

        StudentView studentView = testObject.create(form);
        List<StudentView> actual = testObject.findAll();

        assertTrue(actual.contains(studentView));

    }

    @Test
    void deleteStudent() {

        CreateStudentForm form = new CreateStudentForm
                (1,"Testing", "Testing@testing.com", "Testingstreet");

        //StudentView studentView = testObject.create(form);
        //Boolean actual = testObject.deleteStudent(1);

        //assertTrue(actual);

    }
}

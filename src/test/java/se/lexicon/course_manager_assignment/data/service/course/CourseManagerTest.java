package se.lexicon.course_manager_assignment.data.service.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {

    @Autowired
    private CourseService testObject;

    @Autowired
    private CourseDao courseDao;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(courseDao);
    }

    //Write your tests here

    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }

    @Test
    void create() {

        int actual = CourseSequencer.getCourseSequencer();
        int expected = actual + 1;

        CreateCourseForm form = new CreateCourseForm
                (1,"Testjavaprog", LocalDate.parse("2019-05-17"),1);

        CourseView course = testObject.create(form);

        actual = CourseSequencer.getCourseSequencer();

        assertEquals(expected,actual);
        assertEquals(course.getCourseName(), "Testjavaprog");
        assertEquals(course.getStartDate(), LocalDate.parse("2019-05-17"));
        assertEquals(course.getWeekDuration(),1);

    }

    @Test
    void update() {
    }

    @Test
    void searchByCourseName() {
    }

    @Test
    void searchByDateBefore() {
    }

    @Test
    void searchByDateAfter() {

        CreateCourseForm form = new CreateCourseForm
                (1,"Testjavaprog", LocalDate.parse("2019-05-17"),1);

        CourseView courseView = testObject.create(form);
        List<CourseView> actual = testObject.searchByDateAfter(LocalDate.parse("2019-01-23"));

        assertTrue(actual.contains(courseView));

    }

    @Test
    void addStudentToCourse() {
    }

    @Test
    void removeStudentFromCourse() {
    }

    @Test
    void findById() {

        CreateCourseForm form = new CreateCourseForm
                (1,"Testjavaprog", LocalDate.parse("2019-05-17"),1);

        CourseView courseView = testObject.create(form);
        CourseView actual = testObject.findById(1);

        assertEquals(courseView,actual);

    }

    @Test
    void findAll() {
    }

    @Test
    void findByStudentId() {
    }

    @Test
    void deleteCourse() {
    }
}

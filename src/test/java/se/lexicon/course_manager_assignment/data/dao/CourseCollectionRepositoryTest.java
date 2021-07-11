package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here


    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }

    @Test
    void createCourse() {
        int actual = CourseSequencer.getCourseSequencer();
        int expected = actual + 1;

        Course course = testObject.createCourse
                ("Testing", LocalDate.parse("2019-05-05"),1);

        actual = CourseSequencer.getCourseSequencer();

        assertEquals(expected,actual);
        assertEquals(course.getCourseName(),"Testing");
        assertEquals(course.getStartDate(),LocalDate.parse("2019-05-05"));
        assertEquals(course.getWeekDuration(),1);
    }

    @Test
    void findById() {
    }

    @Test
    void findByNameContains() {
    }

    @Test
    void findByDateBefore() {
    }

    @Test
    void findByDateAfter() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByStudentId() {
        Student student = new Student(1);
        Course course = testObject.createCourse
                ("Testing", LocalDate.parse("2019-05-05"),1);
        Collection<Course> courseCollection;


        course.enrollStudent(student);
        courseCollection = testObject.findByStudentId(1);

        assertTrue(courseCollection.contains(course));





    }

    @Test
    void removeCourse() {
        Course unexpected = testObject.createCourse
                ("Testing", LocalDate.parse("2019-05-05"),1);
        boolean actual = testObject.removeCourse(unexpected);
        assertEquals(actual,true);
    }

    @Test
    void clear() {
        Course expected = testObject.createCourse
                ("Testing", LocalDate.parse("2019-05-05"),1);

        testObject.clear();
        Collection<Course> actual = testObject.findAll();


        assertFalse(actual.contains(expected));
    }
}

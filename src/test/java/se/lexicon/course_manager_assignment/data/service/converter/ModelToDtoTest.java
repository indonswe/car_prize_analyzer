package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }

    @Test
    void studentToStudentView() {
        //testObject.c
        Student expected = new Student(1);
        expected.setName("Testing");
        expected.setEmail("Testing@testing.com");
        expected.setAddress("Testingstreet");

        StudentView studentView = testObject.studentToStudentView(expected);

        assertEquals(expected.getEmail(),studentView.getEmail());


    }

    @Test
    void courseToCourseView() {

        Course expected = new Course(1);
        Collection<Course> courses = new ArrayList<>();
        courses.add(expected);

        CourseView courseView = testObject.courseToCourseView(expected);

        assertEquals(expected.getId(),courseView.getId());

    }

    @Test
    void coursesToCourseViews() {
        Course expected = new Course(1);
        Collection<Course> courses = new ArrayList<>();
        courses.add(expected);

        List<CourseView> courseViews = testObject.coursesToCourseViews(courses);

        assertTrue(courseViews.contains(testObject.courseToCourseView(expected)));

    }

    @Test
    void studentsToStudentViews() {

        Student expected = new Student(1);
        expected.setName("Testing");
        expected.setEmail("Testing@testing.com");
        expected.setAddress("Testingstreet");
        Collection<Student> students = new ArrayList<>();
        students.add(expected);

        List<StudentView> studentViews = testObject.studentsToStudentViews(students);

        assertTrue(studentViews.contains(testObject.studentToStudentView(expected)));

    }

    //write your tests here
}

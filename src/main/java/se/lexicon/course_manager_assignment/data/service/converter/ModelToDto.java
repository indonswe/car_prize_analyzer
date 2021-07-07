package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class  ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        System.out.println("StudentModel - student");
        StudentView studentView = 
                new StudentView
                        (student.getId(),student.getName(),student.getEmail(),student.getAddress());
        return studentView;
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        CourseView courseView = new CourseView(course.getId(),course.getCourseName(),course.getStartDate(),course.getWeekDuration(),null);
        return courseView;
    } 

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<CourseView> courseViewList = new ArrayList<>();
        for (Course course:courses){
            CourseView courseView = new CourseView
                    (course.getId(),course.getCourseName(),
                            course.getStartDate(),course.getWeekDuration(),null);
            courseViewList.add(courseView);
        }
        return courseViewList;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        System.out.println("StudentManager - students");
        List<StudentView> studentViewList = new ArrayList<>();
        for (Student student:students){
            System.out.println("Loop student" + student);
            StudentView studentView = new StudentView
                    (student.getId(),student.getName(),student.getEmail(),student.getAddress());
            System.out.println("Loop studentView" + studentView);
            studentViewList.add(studentView);
        }
        return studentViewList;
    }
}

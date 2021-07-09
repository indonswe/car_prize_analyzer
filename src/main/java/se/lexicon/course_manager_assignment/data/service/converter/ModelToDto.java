package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.*;

@Component
public class  ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        StudentView studentView = 
                new StudentView
                        (student.getId(),student.getName(),
                                student.getEmail(),student.getAddress());
        return studentView;
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        List<StudentView> students = new ArrayList<>();
        for (Student student:course.getStudents()){

            /*StudentView studentView =
                    new StudentView
                            (student.getId(),student.getName(),
                                    student.getEmail(),student.getAddress());*/
            students.add(studentToStudentView(student));
        }
        //students = Collections.singletonList((StudentView) course.getStudents());
        CourseView courseView = new CourseView
                (course.getId(),course.getCourseName(),
                        course.getStartDate(),course.getWeekDuration(),students);
        return courseView;
    } 

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<CourseView> courseViewList = new ArrayList<>();
        for (Course course:courses){
            List<StudentView> students = new ArrayList<>();
            for (Student student:course.getStudents()){
                /*StudentView studentView =
                        new StudentView
                                (student.getId(),student.getName(),
                                        student.getEmail(),student.getAddress());*/
                students.add(studentToStudentView(student));
            }
            CourseView courseView = new CourseView
                    (course.getId(),course.getCourseName(),
                            course.getStartDate(),course.getWeekDuration(),students);
            courseViewList.add(courseView);
        }
        return courseViewList;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        //System.out.println("StudentManager - students");
        List<StudentView> studentViewList = new ArrayList<>();
        for (Student student:students){
            studentViewList.add(studentToStudentView(student));
            /*StudentView studentView = new StudentView
                    (student.getId(),student.getName(),student.getEmail(),student.getAddress());
            studentViewList.add(studentView);*/
        }
        return studentViewList;
    }
}

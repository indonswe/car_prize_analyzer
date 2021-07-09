package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        Student student = studentDao.createStudent(form.getName(),form.getEmail(),form.getAddress());
        StudentView studentView = converters.studentToStudentView(student);
        System.out.println("StudentManager - create");
        return studentView;
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        for (Student student:studentDao.findAll()){
            if (student.getId()==form.getId()) {
                student.setName(form.getName());
                student.setEmail(form.getEmail());
                student.setAddress(form.getAddress());
                StudentView studentView = converters.studentToStudentView(student);
                return studentView;
            }
        }
        return null;
    }

    @Override
    public StudentView findById(int id) {
        for(StudentView studentView:converters.studentsToStudentViews(studentDao.findAll())){
            if (studentView.getId()==id) return studentView;
        }
        return null;
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student = studentDao.findByEmailIgnoreCase(email);
        StudentView studentView = converters.studentToStudentView(student);
        return studentView;
    }

    @Override
    public List<StudentView> searchByName(String name) {
        Collection<Student> students = studentDao.findByNameContains(name);
        List<StudentView> studentViewList = converters.studentsToStudentViews(students);
        return studentViewList;
    }

    @Override
    public List<StudentView> findAll() {
        System.out.println("StudentManager - findAll");
        Collection<Student> students = studentDao.findAll();
        List<StudentView> studentViewList = converters.studentsToStudentViews(students);
        return studentViewList;
    }

    @Override
    public boolean deleteStudent(int id) {
        Collection<Course> coursesNew = new ArrayList<>();
        Collection<Student> studentCollection = new ArrayList<>();
        //
        int saveCourse = 999;
        int saveStudent = 999;
        boolean identifiedInCourse = false;
        //The above is needed because I get an concurrence error if I try to remove
        //student from course in the for loop so instead I save the information
        Student student = studentDao.findById(id);
        coursesNew = courseDao.findAll();
        for (Course course:coursesNew){
            System.out.println("Looping");
            System.out.println(course);
            studentCollection = course.getStudents();
            System.out.println("S C " + studentCollection);
            for (Student studentCourse:studentCollection){
                System.out.println("working");
                if (studentCourse.getId()==id) {
                    System.out.println("Unenroll");
                    saveCourse = course.getId();
                    saveStudent = studentCourse.getId();
                    identifiedInCourse = true;
                }
            }
        }
        if (identifiedInCourse) {
            Boolean unenroll = courseDao.findById
                    (saveCourse).unenrollStudent(studentDao.findById(saveStudent));
        }
        return studentDao.removeStudent(student);
    }
}

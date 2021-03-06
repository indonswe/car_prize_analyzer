package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {
        Course course = courseDao.createCourse
                (form.getCourseName(),form.getStartDate(),form.getWeekDuration(),form.getPrice2021());
        CourseView courseView = converters.courseToCourseView(course);
        return courseView;
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        for (Course course:courseDao.findAll()){
            if (course.getId()==form.getId()) {
                course.setCourseName(form.getCourseName());
                course.setStartDate(form.getStartDate());
                course.setWeekDuration(form.getWeekDuration());
                CourseView courseView = converters.courseToCourseView(course);
                return courseView;
            }
        }
        return null;
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courseDao.findAll()){
            if (course.getCourseName().equals(courseName)) coursesNew.add(course);
        }
        List<CourseView> courseViewList = converters.coursesToCourseViews(coursesNew);
        return courseViewList;
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courseDao.findAll()){
            if (course.getStartDate().isBefore(end)) coursesNew.add(course);
        }
        List<CourseView> courseViewList = converters.coursesToCourseViews(coursesNew);
        return courseViewList;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courseDao.findAll()){
            if (course.getStartDate().isAfter(start)) coursesNew.add(course);
        }
        List<CourseView> courseViewList = converters.coursesToCourseViews(coursesNew);
        return courseViewList;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);
        System.out.println(course);
        if (course==null || student==null) return false;
        return course.enrollStudent(student);
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        System.out.println("sfc");
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);
        if (course==null || student==null) return false;
        return course.unenrollStudent(student);
    }

    @Override
    public CourseView findById(int id) {
        Course course = courseDao.findById(id);
        CourseView courseView = converters.courseToCourseView(course);
        return courseView;
    }

    @Override
    public List<CourseView> findAll() {
        Collection<Course> courses = courseDao.findAll();
        List<CourseView> courseViewList = converters.coursesToCourseViews(courses);
        return courseViewList;
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courseDao.findAll()){
            for(Student student:course.getStudents()){
                if (student.getId()==studentId) coursesNew.add(course);
            }
        }
        List<CourseView> courseViewList = converters.coursesToCourseViews(coursesNew);
        return courseViewList;
    }

    @Override
    public boolean deleteCourse(int id) {
        Course course = courseDao.findById(id);
        return courseDao.removeCourse(course);
        /*CourseView courseView = converters.courseToCourseView(course);
        return courseView;
        for (Course course:courseDao.findAll()){
            if (course.getId()==id) return courseDao.removeCourse(course);
        }
        return false;*/
    }
}

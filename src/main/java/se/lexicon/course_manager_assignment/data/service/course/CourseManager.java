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
                (form.getCourseName(),form.getStartDate(),form.getWeekDuration());
        CourseView courseView = converters.courseToCourseView(course);
        return courseView;
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        return null;
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        return null;
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        return null;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        return null;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);
        return course.enrollStudent(student);
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        return false;
    }

    @Override
    public CourseView findById(int id) {
        for(CourseView courseView:converters.coursesToCourseViews(courseDao.findAll())){
            if (courseView.getId()==id) return  courseView;
        }
        return null;
    }

    @Override
    public List<CourseView> findAll() {
        Collection<Course> courses = courseDao.findAll();
        List<CourseView> courseViewList = converters.coursesToCourseViews(courses);
        return courseViewList;
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        return null;
    }

    @Override
    public boolean deleteCourse(int id) {
        return false;
    }
}

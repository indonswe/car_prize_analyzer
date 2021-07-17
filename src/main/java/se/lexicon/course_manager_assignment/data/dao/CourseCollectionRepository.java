package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;

    public CourseCollectionRepository() { }

    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int nextId = CourseSequencer.nextCourseId();
        Course newCourse = new  Course(nextId);
        newCourse.setCourseName(courseName);
        newCourse.setStartDate(startDate);
        newCourse.setWeekDuration(weekDuration);
        //newCourse.setStudents();
        courses.add(newCourse);
        return newCourse;
    }

    @Override
    public Course findById(int id) {
        if (courses==null) return null;
        for (Course course:courses){
            if (course.getId()==id) return course;
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courses){
            if (course.getCourseName().contains(name)) coursesNew.add(course);
        }
        return coursesNew;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courses){
            if (course.getStartDate().isBefore(end)) coursesNew.add(course);
        }
        return coursesNew;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courses){
            if (course.getStartDate().isAfter(start)) coursesNew.add(course);
        }
        return coursesNew;
    }

    @Override
    public Collection<Course> findAll() {
        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> coursesNew = new ArrayList<>();
        for (Course course:courses){
           for(Student student:course.getStudents()){
               if (student.getId()==studentId) coursesNew.add(course);
            }
        }
        return coursesNew;
    }

    @Override
    public boolean removeCourse(Course course) {
        for(Course i:courses){
            if (i.getId()==course.getId()) return courses.remove(i);
        }
        return false;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}

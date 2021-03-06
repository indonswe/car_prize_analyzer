package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Course {

    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students = new ArrayList<>();
    private int price2021;

    public Course(){

    }

    public Course(int id) {
        this.id = id;
    }

    public boolean enrollStudent(Student student){

        if (student==null) return false;
        boolean addStudent = true;
        for(Student i:students){
            if (student.getId()==i.getId()){
                addStudent = false;
            }else if(student.getEmail().equals(i.getEmail())){
                addStudent = false;
            }
        }
        if (addStudent) addStudent = students.add(student);
        return addStudent;
    }

    public boolean unenrollStudent(Student student){
        boolean removeStudent = students.remove(student);
        return removeStudent;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        //this.students = students;
    }

    public int getPrice2021() {
        return price2021;
    }

    public void setPrice2021(int price2021) {
        this.price2021 = price2021;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            System.out.println("hmm");
            return true;
        }
        System.out.println("yep");
        if (o == null || getClass() != o.getClass()) {
            System.out.println("false");
            return false;
        }
        Course course = (Course) o;
        return id == course.id &&
                weekDuration == course.weekDuration &&
                courseName.equals(course.courseName) &&
                startDate.equals(course.startDate)&&
                students.equals(course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }
}

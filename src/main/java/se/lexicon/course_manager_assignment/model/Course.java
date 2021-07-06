package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students = new ArrayList<>();





    public Course(String courseName, LocalDate startDate, int weekDuration) {
        this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;

    }

}

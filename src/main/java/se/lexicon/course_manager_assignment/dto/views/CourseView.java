package se.lexicon.course_manager_assignment.dto.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CourseView {

    private final int id;
    private final String courseName;
    private final LocalDate startDate;
    private final int weekDuration;
    private final int price2021;
    private List<StudentView> students;

    public CourseView(int id, String courseName, LocalDate startDate, int weekDuration, int price2021, List<StudentView> students) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.price2021 = price2021;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public int getPrice2021() {
        return price2021;
    }

    public List<StudentView> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseView that = (CourseView) o;
        return getId() == that.getId() &&
                getWeekDuration() == that.getWeekDuration() &&
                Objects.equals(getCourseName(), that.getCourseName()) &&
                Objects.equals(getStartDate(), that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseName(), getStartDate(), getWeekDuration());
    }
}

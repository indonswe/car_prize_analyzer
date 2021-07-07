package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        int nextId = StudentSequencer.nextStudentId();
        Student newStudent = new Student(nextId);
        newStudent.setAddress(address);
        newStudent.setEmail(email);
        newStudent.setName(name);
        students.add(newStudent);
        return newStudent;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        Collection<Student> studentCollection = new ArrayList<>();
        for (Student student:students){
            if (student.getName().equals(name)) studentCollection.add(student);
        }
        return studentCollection;
    }

    @Override
    public Student findById(int id) {
        for (Student student:students){
            if (student.getId()==id) return student;
        }
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public boolean removeStudent(Student student) {
        for(Student i:students){
            if (i.getId()==student.getId()) return students.remove(i);
        }
        return false;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}

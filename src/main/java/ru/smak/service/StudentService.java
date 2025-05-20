package ru.smak.service;

import io.ebean.DB;
import ru.smak.model.Student;

import java.util.List;

public class StudentService {
    public void createStudent(Student student) {
        student.save();
    }

    public List<Student> getAllStudents() {
        return DB.find(Student.class).findList();
    }

    public void updateStudent(Long id, String name, int age) {
        Student student = DB.find(Student.class, id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            student.update();
        }
    }

    public void deleteStudent(Long id) {
        Student student = DB.find(Student.class, id);
        if (student != null) {
            student.delete();
        }
    }

    public List<Student> getStudentsByName(String name){
        return DB.find(Student.class).where().ilike("name", "%"+name+"%").findList();
    }
}
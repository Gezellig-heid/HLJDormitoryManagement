package com.example.demo.service;

import com.example.demo.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> getStudentsWithFloorNumber(int floorNumber);

    void addStudent(Student student);

    void update(Student student);

    void delete(int id);

    Map<Integer, Student> getIdAndName(int floorNumber);

    List<String> getAllCounselor(int floorNumber);

    List<Student> getStudentsWithSignInStatus(int floorNumber, String counselor, int i);

    Student getStudentWithId(int id);
}

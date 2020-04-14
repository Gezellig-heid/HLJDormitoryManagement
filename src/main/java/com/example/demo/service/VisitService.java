package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.VisitInfo;

import java.text.ParseException;
import java.util.List;

public interface VisitService {
    //获取当前宿舍楼的来访信息
    List<VisitInfo> getAllVisitOfFloorNumber(int floorNumber);

    //保存来访信息
    void save(VisitInfo visit, int adminId, String s, String vtime) throws ParseException;

    //获取当前宿舍楼所有学生信息
    List<Student> getAllStudentOfFloorNumber(int adminId);

    //检查学生是否存在
    boolean checkStudent(String sname);

    void update(VisitInfo visit, String vtime, String ltime) throws ParseException;

    int findId(String sname, String vtime) throws ParseException;


    void delete(int id);

    Boolean checkVtime(String vtime) throws ParseException;

    boolean checkLtime(String ltime, String vtime) throws ParseException;
}

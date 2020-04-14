package com.example.demo.service;

import com.example.demo.entity.Sign;
import com.example.demo.entity.Student;

import java.util.List;

/**
 * (Sign)表服务接口
 *
 * @author makejava
 * @since 2020-03-14 10:08:14
 */
public interface SignService {


    //获取当前楼层今天的所有签到设置
    List<Sign> getAllSignInMsg(int floorNumber);

    //保存签到信息
    void addSignInMsg(String[] counselors, String startTime, String endTime, int floorNumber);

    //删除签到信息
    void deleteSignInMsg(int id);

    //获取某个学生的签到信息
    Sign getStudentSignInMsg(Student student);

    //学生签到
    void studentSignIn(Student student);
}
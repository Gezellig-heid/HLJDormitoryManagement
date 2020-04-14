package com.example.demo.dao;

import com.example.demo.entity.Sign;
import com.example.demo.entity.Student;
import com.example.demo.mapper.SignMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Sign)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-14 10:08:14
 */

@Repository
public class SignDao {
    @Resource
    SignMapper signMapper;

    public List<Sign> getAllSignInMsg(int floorNumber) {
        return signMapper.getAllSignInMsg(floorNumber);
    }


    public void addSignInMsg(Sign sign) {
        signMapper.addSignInMsg(sign);
    }

    public void deleteSignInMsg(int id) {
        signMapper.deleteSignInMsg(id);
    }


    public int getFloorNumber(int id) {
        return signMapper.getFloorNumber(id);
    }

    public String getCounselor(int id) {
        return signMapper.getCounselor(id);
    }

    public List<Integer> getAllSignInStudents() {
        return signMapper.getAllSignInStudents();
    }

    public Sign getStudentSignInMsg(int floorNumber, String counselor) {
        return signMapper.getStudentSignInMsg(floorNumber,counselor);
    }

    public void studentSignIn(int id) {
        signMapper.studentSignIn(id);
    }
}
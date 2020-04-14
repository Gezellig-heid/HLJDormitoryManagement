package com.example.demo.dao;

import com.example.demo.entity.Cost;
import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Repair;
import com.example.demo.entity.Student;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.DormitoryMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DormitoryDao {
    @Autowired
    DormitoryMapper dormitoryMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    StudentMapper studentMapper;

    //获取该宿舍楼下的所有宿舍信息
    public List<Dormitory> getAllDormitorys(int floorNumber) {
        List<Dormitory> allDormitorys = dormitoryMapper.getAllDormitorys(floorNumber);
        return  allDormitorys;
    }

    public List<Student> getAllStudents(int floorNumber) {
        List<Student> studentsWithFloorNumber = studentMapper.getStudentsWithFloorNumber(floorNumber);
        return studentsWithFloorNumber;
    }

    public void updateDormitoryInfo(Dormitory dormitoryInfo) {
        dormitoryMapper.updateDormitoryInfo(dormitoryInfo);
    }

    public void deleteDormitory(int floorNumber) {
        dormitoryMapper.deleteDormitory(floorNumber);
    }

    public int checkDorPeople(int dorNumber, int floorNumber) {
        return dormitoryMapper.checkDorPeople(dorNumber,floorNumber);
    }

    public int getRoomType(int dorNumber, int floorNumber) {
        return dormitoryMapper.getRoomType(dorNumber,floorNumber);
    }

    public void dorAddPeople(int dorNumber, int floorNumber) {
        dormitoryMapper.dorAddPeople(dorNumber,floorNumber);
    }

    public Cost getDorMiscellaneoust(int floorNumber, int dorNumber) {
        return dormitoryMapper.getDorMiscellaneoust(floorNumber,dorNumber);
    }

}

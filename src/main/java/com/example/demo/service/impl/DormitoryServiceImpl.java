package com.example.demo.service.impl;

import com.example.demo.dao.CommentsDao;
import com.example.demo.dao.CostDao;
import com.example.demo.dao.DormitoryDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Cost;
import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Student;
import com.example.demo.service.DormitoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Resource
    DormitoryDao dormitoryDao;
    @Resource
    CostDao costDao;
    @Resource
    CommentsDao commentsDao;
    @Resource
    StudentDao studentDao;


    //返回宿舍类型
    @Override
    public int getRoomType(int dorNumber, int floorNumber) {
        return dormitoryDao.getRoomType(dorNumber,floorNumber);
    }


    //查询宿人数
    @Override
    public int checkDorPeople(int dorNumber, int floorNumber) {
        return dormitoryDao.checkDorPeople(dorNumber,floorNumber);
    }

    //更新宿舍维修费
    @Override
    public void updateFixFee(int dorNumber, int fixFee, int floorNumber) {
        costDao.updateFixFee(dorNumber,fixFee,floorNumber);
    }

    //更新宿舍电费
    @Override
    public void updateElecFee(int dorNumber, int elecFee,int floorNumber) {
        costDao.updateElecFee(dorNumber,floorNumber,elecFee);
    }

    //上缴电费
    @Override
    public void addElecFee(int elecFee, int floorNumber, int dorNumber) {
        costDao.addElecFee(elecFee,floorNumber,dorNumber);
        //删除消息提示
        List<Student> students = studentDao.getStudentsWithDorNumber(floorNumber,dorNumber);
        for (Student s:students) {
            commentsDao.deleteNotification(s.getId(),3);
        }

    }

    //上缴维修费
    @Override
    public void addFixFee(int floorNumber, int dorNumber, int id) {
        costDao.addFixFee(floorNumber,dorNumber);
        //删除消息提示
        commentsDao.deleteNotification(id,4);
    }

    @Override
    public Map<Integer, Cost> getAllCost(int floorNumber) {
        List<Cost> costList = costDao.getAllCostForFloorNumber(floorNumber);
        Map<Integer, Cost> costMap = new HashMap<>();
        for (Cost cost:costList) {
            costMap.put(cost.getDorNumber(),cost);
        }
        return costMap;
    }

    @Override
    public List<Dormitory> getAllDormitorys(int floorNumber) {
        return dormitoryDao.getAllDormitorys(floorNumber);
    }

    @Override
    public List<Student> getAllStudents(int floorNumber) {
        return dormitoryDao.getAllStudents(floorNumber);
    }

    //更新寝室信息
    @Override
    public void updateDormitoryInfo(Dormitory dormitoryInfo) {
        dormitoryDao.updateDormitoryInfo(dormitoryInfo);
    }

    //获取寝室杂费
    @Override
    public Cost getDorMiscellaneoust(int floorNumber, int dorNumber) {
        Cost cost = dormitoryDao.getDorMiscellaneoust(floorNumber,dorNumber);
        return cost;
    }
}

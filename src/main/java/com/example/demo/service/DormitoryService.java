package com.example.demo.service;

import com.example.demo.entity.Cost;
import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Student;

import java.util.List;
import java.util.Map;

public interface DormitoryService {
    List<Dormitory> getAllDormitorys(int floorNumber);

    List<Student> getAllStudents(int floorNumber);

    void updateDormitoryInfo(Dormitory dormitoryInfo);

    Map<Integer, Cost> getAllCost(int floorNumber);

    void updateElecFee(int dorNumber, int elecFee,int floorNumber);

    void updateFixFee(int dorNumber, int fixFee, int floorNumber);

    int checkDorPeople(int dorNumber, int floorNumber);

    int getRoomType(int dorNumber, int floorNumber);

    Cost getDorMiscellaneoust(int floorNumber, int dorNumber);

    void addElecFee(int elecFee, int floorNumber, int dorNumber);

    void addFixFee(int floorNumber, int dorNumber, int id);
}

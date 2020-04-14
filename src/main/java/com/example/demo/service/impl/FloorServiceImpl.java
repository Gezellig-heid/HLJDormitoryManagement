package com.example.demo.service.impl;

import com.example.demo.dao.DormitoryDao;
import com.example.demo.dao.FloorInfoDao;
import com.example.demo.entity.Floor;
import com.example.demo.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    FloorInfoDao floorInfoDao;



    @Autowired
    DormitoryDao dormitoryDao;

    @Override
    public List<Floor> getAll() {
        return floorInfoDao.getAll();
    }

    @Override
    public List<String> getAllRegion() {
        return floorInfoDao.getAllRegion();
    }

    @Override
    public Map<String, Object> getRegionAndFloorNames() {
        return floorInfoDao.getRegionAndFloorNames();
    }

    @Override
    public void save(Floor floor, int floors, int dorNumOfFloor, int dorType) {
        floorInfoDao.save(floor,floors,dorNumOfFloor,dorType);
    }

    @Override
    public void update(Floor floor) {
        floorInfoDao.update(floor);
    }

    @Override
    public void deleteFloor(int floorNumber) {
        floorInfoDao.deleteFloor(floorNumber);
        dormitoryDao.deleteDormitory(floorNumber);
    }

    @Override
    public Map<Integer, String> getFloorNumberandName() {
        Map<Integer,String> map = new HashMap<>();
        List<Floor> floors = floorInfoDao.getAll();
        for (Floor floor: floors) {
            map.put(floor.getFloorNumber(),floor.getFloorName());
        }
        return map;
    }

    @Override
    public int getFloorNumber(String floorName) {
        return floorInfoDao.getFloorNumber(floorName);
    }
}

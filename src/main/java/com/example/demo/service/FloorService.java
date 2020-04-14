package com.example.demo.service;

import com.example.demo.entity.Floor;

import java.util.List;
import java.util.Map;

public interface FloorService {
    List<Floor> getAll();

    List<String> getAllRegion();

    Map<String, Object> getRegionAndFloorNames();

    void save(Floor floor, int floors, int dorNumOfFloor, int dorType);

    void update(Floor floor);

    void deleteFloor(int floorNumber);

    Map<Integer, String> getFloorNumberandName();

    int getFloorNumber(String floorName);
}

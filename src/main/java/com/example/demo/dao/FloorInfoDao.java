package com.example.demo.dao;

import com.example.demo.component.MyScheduled;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Cost;
import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Floor;
import com.example.demo.mapper.CostMapper;
import com.example.demo.mapper.DormitoryMapper;
import com.example.demo.mapper.FloorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FloorInfoDao {
    @Autowired
    FloorMapper floorMapper;

    @Autowired
    DormitoryMapper dormitoryMapper;

    @Autowired
    CostMapper costMapper;

    private static List<Floor> floors = new ArrayList<>();

    public List<Floor> getAll() {
        //获取所有宿舍楼信息
        floors = floorMapper.getAll();
        return floors;
    }

    public List<String> getAllRegion() {
        //获取所有区域信息
        List<String> allRegion = floorMapper.getAllRegion();
        return allRegion;
    }

    public void save(Floor floor, int floors, int dorNumOfFloor, int dorType) {
        int dorNum;
        int dorNums = floors * dorNumOfFloor;     //宿舍总数
        floor.setDorNums(dorNums);
        floor.setFloorType(dorType);
        //保存宿舍楼信息
        floorMapper.saveFloor(floor);
        //循环创建宿舍和宿舍的杂费信息
        Dormitory dormitory = new Dormitory();
        dormitory.setFloorNumber(floor.getFloorNumber());
        dormitory.setPeople(0);
        dormitory.setRoomType(dorType);
        Cost cost = new Cost();
        cost.setFloorNumber(floor.getFloorNumber());
        cost.setFixFee(0);
        cost.setElecFee(dorType* MyScheduled.ELECMONTH);
        for (int i = 1; i <= floors; i++) {
            for (int j = 1; j <= dorNumOfFloor; j++) {
                if (dorNumOfFloor < 10) dorNum = Integer.parseInt(i+"0"+j);
                else dorNum = Integer.parseInt(i + j + "");
                dormitory.setDorNumber(dorNum);
                cost.setDorNumber(dorNum);
                dormitoryMapper.saveDor(dormitory);
                costMapper.save(cost);
            }
        }


    }

    public Floor selectFloor(int floorNumber) {
        //查询宿舍楼信息
        return floorMapper.selectFloor(floorNumber);
    }

    public void update(Floor floor) {
        //更新宿舍楼信息
        floorMapper.updateFloor(floor);
    }

    public void deleteFloor(int floorNumber) {
        //删除宿舍楼信息
        floorMapper.deleteFloor(floorNumber);
    }


    public Map<String, Object> getRegionAndFloorNames() {
        Map<String, Object> map = new HashMap<>();
        //查询所有宿舍楼所在区域和名称
        List<Map<String, Object>> regionAndFloorNames = floorMapper.getRegionAndFloorNames();
        for (Map<String, Object> regionFloor : regionAndFloorNames) {
            map.put(String.valueOf(regionFloor.get("floorName")), regionFloor.get("region"));
        }
        return map;
    }




    public int getFloorNumber(String floorName) {
        return floorMapper.getFloorNumber(floorName);
    }
}

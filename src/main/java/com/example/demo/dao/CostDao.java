package com.example.demo.dao;

import com.example.demo.entity.Cost;
import com.example.demo.mapper.CostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CostDao {


    @Resource
    CostMapper costMapper;

    public List<Cost> getAllCostForFloorNumber(int floorNumber) {
        return costMapper.getAllCostForFloorNumber(floorNumber);
    }

    //重置电的额度
    public void resetElec(int elecmonth) {
        costMapper.resetElec(elecmonth);
    }

    //每分钟默认减少
    public void reduceElec(int elecsecond, int lowestelec) {
        costMapper.reduceElec(elecsecond,lowestelec);
    }


    public void updateElecFee(int dorNumber, int floorNumber, int elecFee) {
        costMapper.updateElecFee(dorNumber,floorNumber,elecFee);
    }

    public void updateFixFee(int floorNumber,int dorNumber, int fixFee) {
        costMapper.updateFixFee(dorNumber,floorNumber,fixFee);
    }

    public void addElecFee(int elecFee, int floorNumber, int dorNumber) {
        costMapper.addElecFee(elecFee,floorNumber,dorNumber);
    }

    public void addFixFee(int floorNumber, int dorNumber) {
        costMapper.addFixFee(floorNumber,dorNumber);
    }
}

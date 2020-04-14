package com.example.demo.mapper;

import com.example.demo.entity.Cost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CostMapper {
    //按宿舍楼查询所有寝室消费信息
    @Select("select * from cost where floorNumber=#{#floorNumber}")
    List<Cost> getAllCostForFloorNumber(int floorNumber);

    //电费重置
    @Update("update cost s set elecFee=#{elecmonth}*(SELECT roomType from dormitory d where d.floorNumber = s.floorNumber and d.dorNumber = s.dorNumber)")
    void resetElec(int elecmonth);

    //减少电费(到负11)
    @Update("update cost set elecFee=elecFee-#{elecsecond} where elecFee!=#{lowestelec}")
    void reduceElec( int elecsecond,int lowestelec);

    //新建一个宿舍信息
    @Insert("insert into cost(dorNumber,elecFee,fixFee,floorNumber) values(#{dorNumber},#{elecFee},#{fixFee},#{floorNumber})")
    void save(Cost cost);

    //更新宿舍电费
    @Update("update cost set elecFee=elecFee+#{elecFee} where dorNumber=#{dorNumber} and floorNumber=#{floorNumber}")
    void updateElecFee(int dorNumber, int floorNumber, int elecFee);

    //更新宿舍维修费
    @Update("update cost set fixFee=fixFee+#{fixFee} where dorNumber=#{dorNumber} and floorNumber=#{floorNumber}")
    void updateFixFee(int dorNumber, int floorNumber, int fixFee);

    //上缴电费
    @Update("update cost set elecFee = elecFee+#{elecFee} where floorNumber=#{floorNumber} and dorNumber=#{dorNumber}")
    void addElecFee(int elecFee, int floorNumber, int dorNumber);

    //上缴宿舍维修费
    @Update("update cost set fixFee=0 where floorNumber=#{floorNumber} and dorNumber=#{dorNumber}")
    void addFixFee(int floorNumber,int dorNumber);
}

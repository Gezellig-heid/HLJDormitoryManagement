package com.example.demo.mapper;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Floor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface FloorMapper {
    //获取所有宿舍楼信息
    @Select("select * from floor")
    public List<Floor> getAll();

    //获取所有区域信息
    @Select("select distinct region from floor")
    public List<String> getAllRegion();

    //保存宿舍楼信息
    @Options(useGeneratedKeys = true,keyProperty = "floorNumber")
    @Insert("insert into floor(floorName,dorNums,region,floorType) values(#{floorName},#{dorNums},#{region},#{floorType})")
    public void saveFloor(Floor floor);

    //查询宿舍楼信息
    @Select("select * from floor where floorNumber = #{floorNumber}")
    public Floor selectFloor(int floorNumber);

    //删除宿舍楼信息
    @Delete("delete from floor where floorNumber = #{floorNumber}")
    public void deleteFloor(int floorNumber);

    //更新宿舍楼信息
    @Update("update floor set floorName=#{floorName},dorNums=#{dorNums},region=#{region} where floorNumber=#{floorNumber}")
    public void updateFloor( Floor floor);

    //查询所有区域和宿舍楼名称
    @Select("select region,floorName from floor")
    public List<Map<String, Object>> getRegionAndFloorNames();


    //通过名称获取该宿舍楼号
    @Select("select floorNumber from floor where floorName=#{floorName}")
    int getFloorNumber(String floorName);
}

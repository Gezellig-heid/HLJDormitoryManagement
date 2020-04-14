package com.example.demo.mapper;

import com.example.demo.entity.Cost;
import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Repair;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DormitoryMapper {

    //查询该宿舍楼下的所有宿舍信息
    @Select("select * from dormitory where floorNumber=#{floorNumber}")
    public List<Dormitory> getAllDormitorys(int floorNumber);

    //保存宿舍信息
    @Insert("insert into dormitory(dorNumber,floorNumber,people,roomType) values(#{dorNumber},#{floorNumber},#{people},#{roomType})")
    public void saveDor(Dormitory dormitory);

    //更新宿舍信息
    @Update("update dormitory set roomType=#{roomType} where dorNumber=#{dorNumber} and floorNumber=#{floorNumber}")
    public void updateDormitoryInfo(Dormitory dormitoryInfo);

    //删除宿舍信息
    @Delete("delete from dormitory where floorNumber = #{floorNumber}")
    void deleteDormitory(int floorNumber);

    //查询宿舍人数
    @Select("select people from dormitory where floorNumber=#{floorNumber} and dorNumber=#{dorNumber}")
    int checkDorPeople(int dorNumber, int floorNumber);

    //查询宿舍类型
    @Select("select roomType from dormitory where floorNumber=#{floorNumber} and dorNumber=#{dorNumber}")
    int getRoomType(int dorNumber, int floorNumber);

    //添加宿舍人数
    @Update("update dormitory set people=people+1 where dorNumber=#{dorNumber} and floorNumber=#{floorNumber}")
    void dorAddPeople(int dorNumber, int floorNumber);

    //返回宿舍杂费
    @Select("select * from cost where floorNumber = #{floorNumber} and dorNumber = #{dorNumber}")
    Cost getDorMiscellaneoust(int floorNumber, int dorNumber);

}

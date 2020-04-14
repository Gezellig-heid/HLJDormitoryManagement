package com.example.demo.mapper;

import com.example.demo.entity.Sign;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SignMapper {

    //获取当前楼层当天所有签到设置
    @Select("select * from sign where floorNumber = #{floorNumber}")
    List<Sign> getAllSignInMsg(int floorNumber);

    //保存签到信息
    @Insert("insert into sign(startTime,endTime,counselor,checked,unchecked,floorNumber) values(#{starttime},#{endtime},#{counselor},#{checked},#{unchecked},#{floorNumber})")
    void addSignInMsg(Sign sign);

    //删除签到信息
    @Delete("delete from sign where id = #{id}")
    void deleteSignInMsg(int id);


    //获取楼号和辅导员
    @Select("select floorNumber from sign where id=#{id}")
    int getFloorNumber(int id);

    @Select("select counselor from sign where id=#{id}")
    String getCounselor(int id);

    //获取所有需要签到的学生Id
    @Select("select s.id from sign i inner join student s on s.floorNumber = i.floorNumber and i.counselor = s.counselor")
    List<Integer> getAllSignInStudents();

    //获取当前学生的签到信息
    @Select("select * from sign where floorNumber = #{floorNumber} and counselor = #{counselor}")
    Sign getStudentSignInMsg(int floorNumber, String counselor);

    //学生签到
    @Update("update student set sign = 2 where id=#{id}")
    void studentSignIn(int id);
}

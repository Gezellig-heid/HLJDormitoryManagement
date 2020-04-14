package com.example.demo.mapper;


import com.example.demo.entity.Repair;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RepairMapper {

    //获取当前楼的所有上报维修信息
    @Select("select * from repair where floorNumber=#{floorNumber}")
    List<Repair> getAllRepair(int floorNumber);

    //修改维修单状态
    @Update("update repair set status=#{status} where id=#{id}")
    void changeStatus(int id, int status);

    //通过id查询该宿舍号
    @Select("select s.dorNumber from repair r inner join student s on r.id=#{id} and r.studentId=s.id")
    int getDorNumberWithId(int id);

    //通过id得到该宿舍楼
    @Select("select s.floorNumber from repair r inner join student s on r.id=#{id} and r.studentId=s.id")
    int getFloorNumberWithId(int id);

    //通过id获得该学生id
    @Select("select studentId from repair where id=#{id}")
    int getStudentIdWithId(int id);


    //查询宿舍维修工单
    @Select("select * from repair where studentId = #{id}")
    List<Repair> getDorRepair(int id);

    //添加维修工单
    @Insert("insert into repair(studentId,note,imgPath,status,floorNumber) values(#{studentid},#{note},#{imgpath},#{status},#{floornumber})")
    void addDorRepair(Repair repair);
}

package com.example.demo.mapper;

import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StudentMapper {
    //获取该宿舍楼的所有学生信息
    @Select("select * from student s where s.floorNumber=#{floorNumber}")
    public List<Student> getStudentsWithFloorNumber(int floorNumber);

    //添加学生
    @Options(useGeneratedKeys = true,keyProperty = "id") //自增id
    @Insert("insert into student(name,sex,birthday,profession,phoneNumber,counselor,dorNumber,floorNumber,imgPath) values(#{name},#{sex},#{birthday},#{profession},#{phoneNumber},#{counselor},#{dorNumber},#{floorNumber},#{imgPath})")
    public void addStudent(Student student);

    //查询是否存在该学生
    @Select("select count(*) from student where name=#{sname}")
    public int countByName(String sname);

    //查询当前楼发过留言的学生信息
    @Select("select * from student s where s.floorNumber=#{floorNumber} and s.id in (select peopleId from msgboard)")
    List<Student> getAllStudentDorNumberAndMsg(int floorNumber);

    //根据id获取学生信息
    @Select("select * from student where id=#{id}")
    Student getStudentWithId(int id);

    //更新学生信息
    @Update("update student set name=#{name},sex=#{sex},birthday=#{birthday},profession=#{profession},phoneNumber=#{phoneNumber},counselor=#{counselor},dorNumber=#{dorNumber} where id=#{id}")
    void update(Student student);

    //删除学生信息
    @Delete("delete from student where id=#{id}")
    void delete(int id);

    //获取该学生的宿舍楼
    @Select("select floorNumber from student where id=#{id}")
    int getFloorNumberWithId(int id);

    //获取所有寝室欠水费的学生
    @Select("select s.id from student s inner join cost c on s.dorNumber=c.dorNumber and c.floorNumber=s.floorNumber and c.elecFee=#{arrears}")
    List<Integer> getAllArrearsStudents(int arrears);

    //获取当前楼所有辅导员信息
    @Select("select distinct counselor from student where floorNumber=#{floorNumber}")
    List<String> getAllCounselor(int floorNumber);

    //获取该辅导员的学生总数
    @Select("select * from student where floorNumber=#{floorNumber} and counselor=#{counselor}")
    List<Student> getSumStudentsWithCounselor(int floorNumber, String counselor);

    //设置学生的签到标志位
    @Update("update student set sign=#{status} where floorNumber=#{floorNumber} and counselor=#{counselor}")
    void setSignInStatus(int floorNumber, String counselor, int status);

    //按签到状态获取学生
    @Select("select * from student where floorNumber=#{floorNumber} and counselor=#{counselor} and sign=#{status}")
    List<Student> getStudentsWithSignInStatus(int floorNumber, String counselor, int status);

    //重置学生的签到状态
    @Update("update student set sign=1 where id=#{i}")
    void resetSignInStatus(Integer i);

    //更新学生头像
    @Update("update student set imgPath = #{avatars} where id = #{id}")
    void updateStudentImg(int id, String avatars);

    //根据宿舍查询学生信息
    @Select("select * from student where floorNumber=#{floorNumber} and dorNumber=#{dorNumber}")
    List<Student> getStudentsWithDorNumber(int floorNumber, int dorNumber);
}

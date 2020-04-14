package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PersonMapper {

    //获取身份表中所有信息
    @Select("select * from identity")
    public List<Identity> getAllIdentity();

    //查询管理员身份信息
    @Select("select * from admin where id=#{id}")
    Admin findInfoFromAdmin(int id);

    //查询学生身份信息
    @Select("select * from student where id=#{id}")
    Student findInfoFromStudent(int id);

    //更新管理员身份信息
    @Update("update admin set name=#{name},sex=#{sex},birthday=#{birthday},phoneNumber=#{phoneNumber}where id=#{id}")
    void updateAdminInfo(Admin admin);

    //修改密码
    @Update("update identity set password=#{password1} where name=#{id}")
    void changePassword(int password1, String id);

    //保存身份信息
    @Insert("insert into identity(username,password,level,name) values(#{username},#{password},#{level},#{name})")
    void addIdentity(Identity identity);

    //删除管理员身份
    @Delete("delete from identity where name=#{id}")
    void deleteAdmin(int id);

    //更新学生身份信息
    @Update("update student set sex=#{sex},birthday=#{birthday},phoneNumber=#{phoneNumber} where id=#{id}")
    void updateStudentInfo(Student student);
}

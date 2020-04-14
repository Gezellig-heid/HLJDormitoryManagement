package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminMapper {

    //查询所有宿舍管理员信息
    @Select("select * from admin where cast(id as char) like '2%'")
    public List<Admin> getAll();

    //添加宿舍管理员信息
    @Options(useGeneratedKeys = true,keyProperty = "id") //返回自增id
    @Insert("insert into admin(name,sex,birthday,phoneNumber,floorNumber,imgPath) values(#{name},#{sex},#{birthday},#{phoneNumber},#{floorNumber},#{imgPath})")
    public void addAdmin(Admin admin);

    //更新管理员信息
    @Update("update admin set name=#{name},sex=#{sex},birthday=#{birthday},phoneNumber=#{phoneNumber},floorNumber=#{floorNumber} where id=#{id}")
    public void updateAdmin(Admin admin);

    //删除管理员信息
    @Delete("delete from admin where id=#{id}")
    public void deleteAdmin(int id);

    //获取该管理员的宿舍楼id
    @Select("select floorNumber from admin a where a.id=#{adminId}")
    public int getFloorNumberWithAdminId(int adminId);

    //获取当前楼所有管理员信息
    @Select("select * from admin a where a.floorNumber = #{floorNumber}")
    List<Admin> getAdminWithFloorNumber(int floorNumber);

    //通过id获取管理员信息
    @Select("select * from admin where id = #{id}")
    Admin getAdminWithId(int id);

    //更新管理员的头像
    @Update("update admin set imgPath = #{avatars} where id=#{id}")
    void updateAdminImg(int id, String avatars);
}

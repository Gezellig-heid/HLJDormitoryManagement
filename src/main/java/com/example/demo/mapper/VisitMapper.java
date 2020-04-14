package com.example.demo.mapper;

import com.example.demo.entity.VisitInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Mapper
public interface VisitMapper {
    //按时间和楼号查询来访信息（近一周）
    @Select("select * from visit where floorNumber = #{floorNumber} and vtime>=#{before7days}")
    List<VisitInfo> getAllVisitOfFloorNum(int floorNumber, Date before7days);

    //保存来访信息
    @Insert("insert into visit(vname,vsex,sid,sname,floorNumber,relationship,vtime,ltime) values(#{vname},#{vsex},#{sid},#{sname},#{floorNumber},#{relationship},#{vtime},#{ltime})")
    void save(VisitInfo visit);

    //离去修改
    @Update("update visit set ltime=#{ltime} where id=#{id}")
    void leave(VisitInfo visit);

    //更新修改
    @Update("update visit set vname=#{vname},vsex=#{vsex},sname=#{sname},relationship=#{relationship},vtime=#{vtime},ltime=#{ltime} where id=#{id}")
    void update(VisitInfo visit);

    //查询信息编号
    @Select("select id from visit where sname=#{sname} and vtime=#{vdate}")
    int findId(String sname, String vdate);

    //删除来访信息
    @Delete("delete from visit where id=#{id}")
    void delete(int id);
}

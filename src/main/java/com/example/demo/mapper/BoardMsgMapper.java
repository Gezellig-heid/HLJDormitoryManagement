package com.example.demo.mapper;

import com.example.demo.entity.BoardMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface BoardMsgMapper {
    //查询当前楼中所有留言信息
    @Select("(select m.id,m.peopleId,m.title,m.message,m.leaveDate,m.imgpath,a.name from msgboard m inner join admin a on m.peopleId = a.id and a.floorNumber = #{floorNumber} and m.leaveDate>=#{before30days} order by leaveDate desc limit 1000) " +
            "union all " +
            "(select m.id,m.peopleId,m.title,m.message,m.leaveDate,m.imgpath,s.name from msgboard m INNER JOIN student s on m.peopleId = s.id and s.floorNumber = #{floorNumber} and m.leaveDate>=#{before30days} order by leaveDate desc limit 1000)")
    List<Map<String, Object>> getAllMsgForPerson(int floorNumber, Date before30days);

    //保存留言（不带图片）
    @Insert("insert into msgboard(peopleId,title,leaveDate,message) values(#{peopleId},#{title},#{leaveDate},#{message})")
    void saveBoardMsg(BoardMsg boardMsg);

    //删除留言
    @Delete("delete from msgboard where id = #{id}")
    void delete(int id);
}

package com.example.demo.mapper;

import com.example.demo.entity.Comments;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Component
@Mapper
public interface CommentsMapper {

    //查询当前楼层所有评论
    @Select("select m.id,m.leavedate,m.msgId,m.comment,m.peopleId from comments m inner join  admin a on m.peopleId = a.id and a.floorNumber = #{floorNumber} and m.type=1 " +
            "union all " +
            "select m.id,m.leavedate,m.msgId,m.comment,m.peopleId from comments m INNER JOIN student s on m.peopleId = s.id and s.floorNumber = #{floorNumber} and m.type=1 ")
    List<Comments> getAllComs(int floorNumber);

    //保存评论信息
    @Insert("insert into comments(peopleId,msgId,comment,leaveDate,type) values(#{peopleid},#{msgid},#{comment},#{leavedate},#{type})")
    void save(Comments comment);

    //删除评论
    @Delete("delete from comments where id=#{id}")
    void delete(int id);

    //保存消息提示
    @Insert("insert into comments(peopleId,type) values(#{peopleId},#{type})")
    void saveNotification(int peopleId, int type);

    //获取本人的所有消息提示(并且type不为0)
    @Select("select * from comments where peopleId=#{id} and type!=0")
    List<Comments> getNotificationWithPeopleId(int id);

    //删除该用户类型为type的消息提示
    @Delete("delete from comments where peopleId=#{peopleId} and type=#{type}")
    void deleteNotification(int peopleId, int type);

    //如果是评论的话则不进行删除而是将type置0
    @Update("update comments set type=0 where peopleId=#{peopleId} and type=#{type}")
    void deleteComNotification(int peopleId, int type);

    //重置电费时删除所有电费提示
    @Delete("delete from comments where type=#{type}")
    void deleteAllElecNotification(int type);
}

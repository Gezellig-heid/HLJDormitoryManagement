package com.example.demo.service;

import com.example.demo.entity.Comments;
import java.util.List;
import java.util.Map;

/**
 * (Comments)表服务接口
 *
 * @author makejava
 * @since 2020-03-07 21:52:53
 */
public interface CommentsService {


    //按楼号获取所有评论信息
    List<Comments> getAllComWithFloorNumber(int floorNumber);

    //保存评论信息
    void save(int id, String comment, int msgId);

    void delete(int id);

    //获取本人的所有消息提示
    Map<Integer, Integer> getNotificationWithPeopleId(int id);

    void deleteNotification(int id, int type);
}
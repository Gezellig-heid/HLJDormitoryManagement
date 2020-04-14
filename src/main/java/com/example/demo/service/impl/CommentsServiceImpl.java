package com.example.demo.service.impl;

import com.example.demo.component.MyApplicationEvent;
import com.example.demo.entity.Comments;
import com.example.demo.dao.CommentsDao;
import com.example.demo.service.CommentsService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Comments)表服务实现类
 *
 * @author makejava
 * @since 2020-03-07 21:52:53
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    @Resource
    ApplicationContext applicationContext;

    @Resource
    private CommentsDao commentsDao;

    @Override
    public void save(int id, String src, int msgId) {
        Comments comment = new Comments();
        comment.setComment(src);
        comment.setPeopleid(id);
        comment.setMsgid(msgId);
        comment.setComment(src);
        comment.setLeavedate(new Date());
        comment.setType(1);
        commentsDao.save(comment);
    }

    @Override
    public List<Comments> getAllComWithFloorNumber(int floorNumber) {
        List<Comments> allComWithFloorNumber = commentsDao.getAllComWithFloorNumber(floorNumber);
        return allComWithFloorNumber;
    }

    @Override
    public void delete(int id) {
        commentsDao.delete(id);
    }

    @Override
    public Map<Integer, Integer> getNotificationWithPeopleId(int id) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Comments> comments = commentsDao.getNotificationWithPeopleId(id);
        for (Comments c: comments) {
            if(map.get(c.getType())==null){
                map.put(c.getType(),1);
            }else {
                map.put(c.getType(),map.get(c.getType())+1);
            }
        }
        return map;

    }

    @Override
    public void deleteNotification(int id, int type) {
        commentsDao.deleteNotification(id,type);
    }
}
package com.example.demo.dao;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Student;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.CommentsMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Comments)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-07 21:52:53
 */
@Repository
public class CommentsDao {

    public static final int ARREARS = -10;  //-10度电费进行消息提示

    @Resource
    CommentsMapper commentsMapper;
    @Resource
    StudentMapper studentMapper;

    public List<Comments> getAllComWithFloorNumber(int floorNumber) {
        List<Comments> allComs = commentsMapper.getAllComs(floorNumber);
        return allComs;
    }

    public void save(Comments comment) {
        commentsMapper.save(comment);
    }

    public void delete(int id) {
        commentsMapper.delete(id);
    }

    //保存密码消息提示
    public void saveNotification(int peopleId, int type) {
        commentsMapper.saveNotification(peopleId,type);
    }

    //保存费用消息提示
    public void saveFeeNotification(int type) {
        List<Integer> studentsId = studentMapper.getAllArrearsStudents(ARREARS);
        if(studentsId==null||studentsId.size()==0){
            return;
        }else {
            for (int i : studentsId) {
                commentsMapper.saveNotification(i, type);
            }
        }
    }

    //通过用户ID获取该用户的所有消息提示
    public List<Comments> getNotificationWithPeopleId(int id) {
        return commentsMapper.getNotificationWithPeopleId(id);
    }

    //删除该用户类型为type的消息提示
    public void deleteNotification(int peopleId, int type) {
        if(type==1){
            //如果是评论的话则不进行删除而是将type置0
            commentsMapper.deleteComNotification(peopleId,type);
        }else if(type==3){
            commentsMapper.deleteAllElecNotification(type);
        }else {
            commentsMapper.deleteNotification(peopleId,type);
        }
    }
}
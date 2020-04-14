package com.example.demo.service.impl;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.BoardMsgDao;
import com.example.demo.dao.CommentsDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.BoardMsg;
import com.example.demo.entity.Student;
import com.example.demo.service.BoardMsgService;
import com.example.demo.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BoardMsgServiceImpl implements BoardMsgService {


    @Resource
    BoardMsgDao boardMsgDao;
    @Resource
    StudentDao studentDao;
    @Resource
    AdminDao adminDao;
    @Resource
    CommentsDao commentsDao;


    @Override
    public List<Map<String, Object>> getAllMsgForPerson(int floorNumber, int peopleId) {
        //获取30天以内的留言
        Date before30days = DateUtil.getDate(-30);
        List<Map<String, Object>> boardMsgList = boardMsgDao.getAllMsgForPerson(floorNumber,before30days);
        //更新留言提示
        commentsDao.deleteNotification(peopleId,1);
        return boardMsgList;
    }


    //获取所有人名和id的对应
    @Override
    public Map<Integer, String> getAllPersonId(int floorNumber) {
        Map<Integer,String> map = new HashMap<>();
        List<Student> studentsWithFloorNumber = studentDao.getStudentsWithFloorNumber(floorNumber);
        List<Admin> adminWithFloorNumber = adminDao.getAdminWithFloorNumber(floorNumber);
        for (Student student:studentsWithFloorNumber) {
            map.put(student.getId(),student.getName());
        }
        for(Admin admin:adminWithFloorNumber){
            map.put(admin.getId(),admin.getName());
        }
        return map;
    }


    @Override
    public Map<Integer, Integer> getAllStudentDorNumber(int floorNumber) {
        Map<Integer,Integer> studentDorNumberMap = new HashMap<>();
        List<Student> students = studentDao.getAllStudentDorNumberAndMsg(floorNumber);
        for (Student student: students) {
            studentDorNumberMap.put(student.getId(),student.getDorNumber());
        }
        return studentDorNumberMap;
    }

    @Override
    public void saveBoardMsg(int id, String title, String message) {
        BoardMsg boardMsg = new BoardMsg();
        boardMsg.setPeopleId(id);
        boardMsg.setLeaveDate(new Date());
        boardMsg.setTitle(title);
        boardMsg.setMessage(message);
        boardMsgDao.saveBoardMsg(boardMsg);
    }

    @Override
    public void delete(int id) {
        boardMsgDao.delete(id);
    }
}

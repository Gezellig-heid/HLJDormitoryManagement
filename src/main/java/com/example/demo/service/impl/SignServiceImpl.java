package com.example.demo.service.impl;

import com.example.demo.component.MyApplicationEvent;
import com.example.demo.dao.CommentsDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Sign;
import com.example.demo.dao.SignDao;
import com.example.demo.entity.Student;
import com.example.demo.service.SignService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * (Sign)表服务实现类
 *
 * @author makejava
 * @since 2020-03-14 10:08:14
 */
@Service("signService")
public class SignServiceImpl implements SignService {
    @Resource
    private SignDao signDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private CommentsDao commentsDao;

    @Resource
    ApplicationContext applicationContext;



    //获取当前楼层今天的所有签到设置
    @Override
    public List<Sign> getAllSignInMsg(int floorNumber) {
        return signDao.getAllSignInMsg(floorNumber);
    }



    @Override
    public void addSignInMsg(String[] counselors, String startTime, String endTime, int floorNumber) {
        Sign sign = new Sign();
        sign.setStarttime(LocalTime.parse(startTime));
        sign.setEndtime(LocalTime.parse(endTime));
        sign.setFloorNumber(floorNumber);
        int studentNum;
        for (String counselor:counselors) {
            //创建签到信息
            List<Student> students = studentDao.getSumStudentsWithCounselor(floorNumber,counselor);
            studentNum = students.size();
            sign.setChecked(studentNum);
            sign.setUnchecked(studentNum);
            sign.setCounselor(counselor);
            signDao.addSignInMsg(sign);
            //设置他们学生的签到标志位
            studentDao.setSignInStatus(floorNumber,counselor,1);
            for (Student s:students) {
                applicationContext.publishEvent(new MyApplicationEvent(new Object(),s.getId(),5));
            }
        }
    }

    //删除签到信息
    @Override
    public void deleteSignInMsg(int id) {
        //按id获取楼号和辅导员(先取)
        int floorNumber = signDao.getFloorNumber(id);
        String counselor = signDao.getCounselor(id);
        signDao.deleteSignInMsg(id);
        //将学生的标志设置为0
        studentDao.setSignInStatus(floorNumber,counselor,0);
        //删除学生提示
        List<Student> studentList = studentDao.getSumStudentsWithCounselor(floorNumber,counselor);
        for (Student s:studentList) {
            commentsDao.deleteNotification(s.getId(),5);
        }
    }

    @Override
    public void studentSignIn(Student student) {
        signDao.studentSignIn(student.getId());
    }

    //获取某个学生的签到信息
    @Override
    public Sign getStudentSignInMsg(Student student) {
        int floorNumber = student.getFloorNumber();
        String counselor = student.getCounselor();
        return signDao.getStudentSignInMsg(floorNumber,counselor);
    }
}
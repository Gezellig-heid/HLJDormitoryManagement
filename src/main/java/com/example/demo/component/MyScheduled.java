package com.example.demo.component;

import com.example.demo.dao.CommentsDao;
import com.example.demo.dao.CostDao;
import com.example.demo.dao.SignDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Comments;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务类
 */

@Async
@Component
@EnableScheduling
public class MyScheduled {

    @Resource
    ApplicationContext applicationContext;

    public static final int ELECMONTH = 10; //每人10度电
    public static final int ELECSECOND = 1; //每分钟减少1度电
    public static final int LOWESTELEC = -11;   //最多减少-11度


    @Resource
    CostDao costDao;

    @Resource
    CommentsDao commentsDao;

    @Resource
    SignDao signDao;

    @Resource
    StudentDao studentDao;

    /**
     * 测试每5秒钟一次
     */
//    @Scheduled(cron = "0/5 * * * * *")
//    public void scheduled(){
//        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }

    /**
     * 每小时重置宿舍水电费
     * 电费每人每时10度
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void resetElec(){
        costDao.resetElec(ELECMONTH);
        commentsDao.deleteNotification(0,3);
        System.out.println("重置运行"+LocalDateTime.now());
    }

    /**
     * 每分钟减少所有宿舍一度电
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void reduceElec(){
        costDao.reduceElec(ELECSECOND,LOWESTELEC);
        applicationContext.publishEvent(new MyApplicationEvent(new Object(),0,3));
        System.out.println("减少运行"+LocalDateTime.now());
    }

    /**
     * 每天早上7点提醒学生签到信息
     */

    public void signInComment(){
        List<Integer> studentsId = signDao.getAllSignInStudents();

        System.out.println();
    }

    /**
     * 每天零点清除学生签到状态,提醒学生签到信息
     */
    @Scheduled(cron = "* * 0 * * ?")
    public void resetSignIn(){
        List<Integer> studentsId = signDao.getAllSignInStudents();
        for (Integer i:studentsId) {
            studentDao.resetSignInStatus(i);
            commentsDao.saveNotification(i,5);
        }
        System.out.println("清除签到状态运行"+LocalDateTime.now()+"提醒签到运行"+LocalDateTime.now());
    }
}

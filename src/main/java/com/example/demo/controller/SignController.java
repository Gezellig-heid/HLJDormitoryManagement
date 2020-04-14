package com.example.demo.controller;

import com.example.demo.entity.Sign;
import com.example.demo.entity.Student;
import com.example.demo.service.CommentsService;
import com.example.demo.service.SignService;
import com.example.demo.service.StudentService;
import com.example.demo.utils.MsgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Map;

/**
 * (Sign)表控制层
 *
 * @author makejava
 * @since 2020-03-14 10:08:14
 */
@Controller
public class SignController {
    @Resource
    SignService signService;
    @Resource
    StudentService studentService;
    @Resource
    CommentsService commentsService;

    //展示本楼签到信息
    @GetMapping("/signin")
    public String getAllSignInMsg(HttpSession session, Model model){
        int floorNumber = (int)session.getAttribute("floorNumber");
        //获取当前楼层今天的所有签到设置
        List<Sign> signList = signService.getAllSignInMsg(floorNumber);
        model.addAttribute("signs",signList);
        //获取当前楼层所有辅导员信息
        List<String> counselors = studentService.getAllCounselor(floorNumber);
        model.addAttribute("counselors",counselors);
        return "admin/signintable";
    }

    //保存签到设置
    @PostMapping("/signin")
    public String addSignInMsg(HttpSession session,String[] counselors,String startTime,String endTime){
        int floorNumber = (int)session.getAttribute("floorNumber");
        signService.addSignInMsg(counselors,startTime,endTime,floorNumber);
        return "redirect:/signin";
    }

    //删除签到设置
    @DeleteMapping("/signin/{id}")
    public String deleteSignInMsg(@PathVariable("id") int id){
        signService.deleteSignInMsg(id);
        return "redirect:/signin";
    }


    //获取当前学生的签到信息
    @GetMapping("/studentsignin")
    public String getStudentSignInMsg(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("loginuser");
        Sign sign = signService.getStudentSignInMsg(student);
        model.addAttribute("sign",sign);
        LocalTime now = LocalTime.now();
        MsgUtil msgUtil = null;
        //比较两个时间的大小
        if(sign!=null) {
            int com1 = sign.getStarttime().compareTo(now);
            int com2 = sign.getEndtime().compareTo(now);
            model.addAttribute("com1", com1);
            model.addAttribute("com2", com2);
            model.addAttribute("status", student.getSign());
        }
        return "student/signin";
    }

    //当前学生进行签到
    @ResponseBody
    @RequestMapping("/studentsignin")
    public MsgUtil studentSignIn(HttpSession session){
        Student student = (Student)session.getAttribute("loginuser");
        signService.studentSignIn(student);
        //清除消息提醒
        commentsService.deleteNotification(student.getId(),5);
        //重新加载消息提示
        Map<Integer,Integer> map = commentsService.getNotificationWithPeopleId(student.getId());
        session.setAttribute("notification",map);
        Student newStudent = studentService.getStudentWithId(student.getId());
        session.setAttribute("loginuser",newStudent);
        return MsgUtil.success();
    }


    //获取未签到的学生
    @ResponseBody
    @RequestMapping("/findUnchecked")
    public List<Student> findUnchecked(HttpSession session,@RequestParam("counselor") String counselor){
        int floorNumber = (int)session.getAttribute("floorNumber");
        List<Student> students = studentService.getStudentsWithSignInStatus(floorNumber,counselor,1);
        return students;
    }


}
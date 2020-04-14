package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.service.CommentsService;
import com.example.demo.service.FloorService;
import com.example.demo.service.PersonService;
import com.example.demo.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 负责个人信息的查询及其修改、退出登录
 */
@Controller
public class PersonController {
    @Resource
    PersonService personService;
    @Resource
    FloorService floorService;
    @Resource
    CommentsService commentsService;

    //退出登录
    @GetMapping("/quit")
    public String quit(HttpSession session){
        session.removeAttribute("loginuser");
        session.removeAttribute("floorNumber");
        session.removeAttribute("notification");
        return "redirect:/index.html";
    }

    //查询个人信息
    @GetMapping("/person")
    public String findInfo(HttpSession session, Model model){
        Person person = (Person)session.getAttribute("loginuser");
        int id = person.getId();
        String sid = id+"";
        char ch = sid.charAt(0);
        Map<Integer,String> nameAndNumber = floorService.getFloorNumberandName();
        model.addAttribute("nameAndNumber",nameAndNumber);
        if(ch == '2') {
            Admin admin = (Admin)personService.findInfo(id);
            model.addAttribute("profile",admin);
            return "admin/profile";
        }else if(ch == '3'){
            Student student = (Student)personService.findInfo(id);
            model.addAttribute("profile",student);
            return "student/profile";
        }else{
            Admin admin = (Admin)personService.findInfo(id);
            model.addAttribute("profile",admin);
            return "systemadmin/profile";
        }
    }

    //修改密码
    @ResponseBody
    @RequestMapping("/changePassword")
    public String changePassword(HttpSession session,@RequestParam("password1") int password1,@RequestParam("password2") int password2,@RequestParam("id") String id){
        if(password1!=password2){
            return "两次输入密码不一致";
        }
        personService.changePassword(password1,id);
        //重置session中的消息提示
        Map<Integer,Integer> map = commentsService.getNotificationWithPeopleId(Integer.valueOf(id));
        session.setAttribute("notification",map);
        return "修改成功";
    }


    //修改管理员个人信息
    @PutMapping("/adminInfo")
    public String updateAdmin(HttpSession session,@RequestParam("img") MultipartFile img,Admin admin){
        MsgUtil msg = personService.updateAdminInfo(img,admin);
        Person person = personService.getPersonForId(admin.getId());
        session.setAttribute("loginuser",person);
        //更新session信息

        if(msg.getCode() == 100){
            return "redirect:/person";
        }else {
            return "";
        }
    }

    //修改学生个人信息
    @PutMapping("/studentInfo")
    public String updateStudent(HttpSession session,@RequestParam("img") MultipartFile img,Student student){
        MsgUtil msg = personService.updateStudentInfo(img,student);
        Person person = personService.getPersonForId(student.getId());
        session.setAttribute("loginuser",person);
        if(msg.getCode() == 100){
            return "redirect:/person";
        }else {
            return "";
        }
    }

}

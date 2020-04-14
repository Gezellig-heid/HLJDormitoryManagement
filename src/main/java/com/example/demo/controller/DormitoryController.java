package com.example.demo.controller;

import com.example.demo.entity.Cost;
import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Repair;
import com.example.demo.entity.Student;
import com.example.demo.service.CommentsService;
import com.example.demo.service.DormitoryService;
import com.example.demo.service.RepairService;
import com.example.demo.service.StudentService;
import com.example.demo.utils.MsgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DormitoryController {
    @Resource
    DormitoryService dormitoryService;

    @Resource
    StudentService studentService;

    @Resource
    RepairService repairService;

    @Resource
    CommentsService commentsService;


    /**
     * 管理员操作
     */

    //查询宿舍楼所有宿舍信息
    @GetMapping("/dormitory")
    public String getAllDormitorys(HttpSession session, Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        int floorNumber = (int) session.getAttribute("floorNumber");
        List<Dormitory> allDormitorys = dormitoryService.getAllDormitorys(floorNumber);
        model.addAttribute("dormitorys",allDormitorys);
        List<Student> allStudents = dormitoryService.getAllStudents(floorNumber);
        model.addAttribute("studentDorNumList",allStudents);
        List<String> stringRoomType = new ArrayList<>(Arrays.asList("0","1","双人间","3","四人间","5","六人间","7","八人间"));
        model.addAttribute("stringRoomType",stringRoomType);
        Map<Integer, Cost> allCost = dormitoryService.getAllCost(floorNumber);
        model.addAttribute("costs",allCost);
        return "admin/dormitorytable";
    }

    //更新宿舍信息
    @PutMapping("/dormitory")
    public String updateDormitory(HttpSession session,Dormitory dormitoryInfo){
        int floorNumber = (int) session.getAttribute("floorNumber");
        dormitoryInfo.setFloorNumber(floorNumber);
        dormitoryService.updateDormitoryInfo(dormitoryInfo);
        return "redirect:/dormitory";
    }

    //更新宿舍电费
    @PutMapping("/elecFee")
    public String updateElecFee(HttpSession session,int dorNumber,int elecFee){
        int floorNumber = (int) session.getAttribute("floorNumber");
        dormitoryService.updateElecFee(dorNumber,elecFee,floorNumber);
        return "redirect:/dormitory";
    }

    //更新宿舍维修费
    @PutMapping("/fixFee")
    public String updateFixFee(HttpSession session,int dorNumber,int fixFee){
        int floorNumber = (int) session.getAttribute("floorNumber");
        dormitoryService.updateFixFee(dorNumber,fixFee,floorNumber);
        return "redirect:/dormitory";
    }


    /**
     * 学生操作
     */
    //查询宿舍杂费
    @GetMapping("/miscellaneous")
    public String getDorMiscellaneous(HttpSession session, Model model){
        Student student = (Student) session.getAttribute("loginuser");
        Cost dorMiscellaneoust = dormitoryService.getDorMiscellaneoust(student.getFloorNumber(), student.getDorNumber());
        model.addAttribute("dorMiscellaneoust",dorMiscellaneoust);
        return "student/miscellaneous";
    }

    //上缴电费
    @PostMapping("/elecFee")
    public String addElecFee(HttpSession session,int elecFee){
        Student student = (Student) session.getAttribute("loginuser");
        dormitoryService.addElecFee(elecFee,student.getFloorNumber(),student.getDorNumber());
        //将session中的消息提示重置
        Map<Integer,Integer> notificationMap =  commentsService.getNotificationWithPeopleId(student.getId());
        session.setAttribute("notification",notificationMap);
        return "redirect:/miscellaneous";
    }


    //上缴维修费
    @PostMapping("/fixFee")
    public String addFixFee(HttpSession session){
        Student student = (Student) session.getAttribute("loginuser");
        dormitoryService.addFixFee(student.getFloorNumber(),student.getDorNumber(),student.getId());
        //将session中的消息提示重置
        Map<Integer,Integer> notificationMap =  commentsService.getNotificationWithPeopleId(student.getId());
        session.setAttribute("notification",notificationMap);
        return "redirect:/miscellaneous";
    }

    //转到宿舍维修工单
    @GetMapping("/dorrepair")
    public String getDorRepair(HttpSession session,Model model){
        Student student = (Student) session.getAttribute("loginuser");
        List<Repair> repairs = repairService.getDorRepair(student.getId());
        model.addAttribute("repairs",repairs);
        Map<Integer,Student> map = studentService.getIdAndName(student.getFloorNumber());
        model.addAttribute("students",map);
        Map<Integer,String> statusMap = new HashMap<>();
        statusMap.put(1,"待审核");
        statusMap.put(2,"已审核");
        statusMap.put(3,"已完成");
        statusMap.put(4,"已拒绝");
        model.addAttribute("statusMap",statusMap);
        return "student/repairtable";
    }

    //提交维修工单
    @PostMapping("/dorrepair")
    public String addDorRepair(HttpSession session,@RequestParam("img") MultipartFile img,String note){
        Student student = (Student) session.getAttribute("loginuser");
        repairService.addDorRepair(student,img,note);
        return "redirect:/dorrepair";
    }



    //查询宿舍人数
    @ResponseBody
    @RequestMapping("/checkDorPeople")
    public MsgUtil checkDorPeople(HttpSession session,@RequestParam("dorNumber") int dorNumber){
        int floorNumber = (int) session.getAttribute("floorNumber");
        int people = dormitoryService.checkDorPeople(dorNumber,floorNumber);
        int roomType = dormitoryService.getRoomType(dorNumber,floorNumber);
        if(roomType-people>=1){
            return MsgUtil.success();
        }else{
            return MsgUtil.error();
        }
    }



}

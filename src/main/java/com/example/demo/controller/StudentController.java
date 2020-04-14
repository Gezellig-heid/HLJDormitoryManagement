package com.example.demo.controller;

import com.example.demo.entity.Dormitory;
import com.example.demo.entity.Student;
import com.example.demo.service.DormitoryService;
import com.example.demo.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学生控制层
 */

@Controller
public class StudentController {
    @Resource
    StudentService studentService;

    @Resource
    DormitoryService dormitoryService;

    //按宿舍楼编号获取学生
    @GetMapping("/studenttable")
    public String getStudentsWithFloorNumber(@RequestParam(value = "pn",defaultValue = "1")int pn,HttpSession session,Model model){
        int floorNumber = (int) session.getAttribute("floorNumber");
        PageHelper.startPage(pn,20);
        List<Student> students = studentService.getStudentsWithFloorNumber(floorNumber);
        PageInfo pageInfo = new PageInfo(students);
        model.addAttribute("pageInfo",pageInfo);
        //获取所有宿舍号用于宿舍号验证
        List<Dormitory> allDormitorys = dormitoryService.getAllDormitorys(floorNumber);
        model.addAttribute("allDormitorys",allDormitorys);
        return "admin/studenttable";
    }

    //添加学生信息
    @PostMapping("/student")
    public String addStuednt(HttpSession session,Student student){
        int floorNumber = (int) session.getAttribute("floorNumber");
        student.setFloorNumber(floorNumber);
        studentService.addStudent(student);
        return "redirect:/studenttable";
    }

    //修改学生信息
    @PutMapping("/student")
    public String updateStudent(Student student){
        studentService.update(student);
        return "redirect:/studenttable";
    }

    //删除学生信息
    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        studentService.delete(id);
        return "redirect:/studenttable";
    }
}

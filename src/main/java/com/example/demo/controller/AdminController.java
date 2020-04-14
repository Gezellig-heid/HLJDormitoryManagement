package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.service.FloorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Resource
    private AdminService adminService;

    @Resource
    private FloorService floorService;

    //查询所有宿舍管理员信息
    @GetMapping("/admintable")
    public String toDorTable(Model model){
        List<Admin> all = adminService.getAll();
        model.addAttribute("admins",all);
        Map<Integer,String> map = floorService.getFloorNumberandName();
        model.addAttribute("numAndName",map);
        Map<String,Object> regionAndFloorNames = floorService.getRegionAndFloorNames();
        model.addAttribute("regionFloorNames",regionAndFloorNames);
        //未完成：无法在修改员工时显示宿舍楼所在区域
        List<String> allRegion = floorService.getAllRegion();
        model.addAttribute("regions",allRegion);
        return "systemadmin/doradmintable";
    }

    //添加宿舍管理员信息
    @PostMapping("/admin")
    public String addAdmin(Admin admin,String floorName){
        int floorNumber = floorService.getFloorNumber(floorName);
        admin.setFloorNumber(floorNumber);
        adminService.addAdmin(admin);
        return "redirect:/admintable";
    }

    //更新宿舍管理员的信息
    @PutMapping("/admin")
    public String updateAdmin(Admin admin,String floorName){
        int floorNumber = floorService.getFloorNumber(floorName);
        admin.setFloorNumber(floorNumber);
        adminService.updateAdmin(admin);
        return "redirect:/admintable";
    }

    //删除管理员信息
    @DeleteMapping("/admin/{id}")
    public String deleteAdmin(@PathVariable("id") int id){
        adminService.deleteAdmin(id);
        return "redirect:/admintable";
    }

}

package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Floor;
import com.example.demo.service.AdminService;
import com.example.demo.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统管理员控制
 */
@Controller
public class FloorController {
    @Resource
    private FloorService floorService;

    @Resource
    private AdminService adminService;

    //查询所有宿舍楼信息
    @GetMapping("/dortable")
    public String toDorTable(Model model){
        List<Floor> all = floorService.getAll();
        model.addAttribute("floors",all);
        List<String> allRegion = floorService.getAllRegion();
        model.addAttribute("regions",allRegion);
        Map<String, Object> regionAndFloorNames = floorService.getRegionAndFloorNames();
        model.addAttribute("regionFloorNames",regionAndFloorNames);
        List<Admin> admins = adminService.getAll();
        model.addAttribute("admins",admins);
        List<String> stringRoomType = new ArrayList<>(Arrays.asList("0","1","双人间","3","四人间","5","六人间","7","八人间"));
        model.addAttribute("stringRoomType",stringRoomType);

        return "systemadmin/floortable";
    }

    //转到宿舍楼添加页面
    @GetMapping("/floor")
    public String toFloorAdd(Model model){
        List<String> allRegion = floorService.getAllRegion();
        model.addAttribute("regions",allRegion);
        return "systemadmin/addfloor";
    }

    //添加宿舍楼
    @PostMapping("/floor")
    public String floorAdd(Floor floor, int floors, int dorNumOfFloor, int dorType){
        floorService.save(floor,floors,dorNumOfFloor,dorType);
        return "redirect:/dortable";
    }


    //修改宿舍楼信息
    @PutMapping("/floor")
    public String modifyFloor(Floor floor){
        floorService.update(floor);
        return "redirect:/dortable";
    }

    //删除宿舍楼(delete模式)
    @DeleteMapping("/floor/{floorNumber}")
    public String deFloor(@PathVariable("floorNumber") int floorNumber){
        floorService.deleteFloor(floorNumber);
        return "redirect:/dortable";
    }

}

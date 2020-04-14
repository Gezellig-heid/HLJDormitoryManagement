package com.example.demo.controller;

import com.example.demo.entity.Repair;
import com.example.demo.entity.Student;
import com.example.demo.service.RepairService;
import com.example.demo.service.StudentService;
import com.example.demo.utils.MsgUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Repair)表控制层
 *
 * @author makejava
 * @since 2020-03-11 09:58:01
 */
@Controller
public class RepairController {
    /**
     * 服务对象
     */
    @Resource
    private RepairService repairService;

    @Resource
    private StudentService studentService;


    //获取当前楼的所有上报的维修信息
    @GetMapping("/repair")
    public String getAllRepair(@RequestParam(value = "pn",defaultValue = "1")int pn, HttpSession session, Model model){
        int floorNumber = (int)session.getAttribute("floorNumber");
        PageHelper.startPage(pn,20);
        List<Repair> repairList = repairService.getAllRepair(floorNumber);
        PageInfo pageInfo = new PageInfo(repairList);
        model.addAttribute("pageInfo",pageInfo);
        Map<Integer,Student> map = studentService.getIdAndName(floorNumber);
        model.addAttribute("students",map);
        Map<Integer,String> statusMap = new HashMap<>();
        statusMap.put(1,"待审核");
        statusMap.put(2,"已审核");
        statusMap.put(3,"已完成");
        statusMap.put(4,"已拒绝");
        model.addAttribute("statusMap",statusMap);
        return "admin/repairtable";
    }

    //修改维修状态
    @ResponseBody
    @RequestMapping("/status")
    public MsgUtil changeStatus(@RequestParam("id") int id,@RequestParam("status") int status,@RequestParam("artificial") int artificial){
        repairService.changeStatus(id,status,artificial);
        return MsgUtil.success();
    }
}
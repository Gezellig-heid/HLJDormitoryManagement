package com.example.demo.controller;

import com.example.demo.entity.VisitInfo;
import com.example.demo.service.VisitService;
import com.example.demo.utils.MsgUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class VisitController {
    @Resource
    VisitService visitService;

    //楼号编号
    static int floorNumber;

    //检验离开时间
    @ResponseBody
    @RequestMapping("checkltime")
    public MsgUtil checkLTime(@RequestParam("ltime") String ltime,@RequestParam("vtime") String vtime) throws ParseException {
        if(StringUtils.isEmpty(ltime)){
            //离开时间为空
            MsgUtil.success();
        }
        boolean b = visitService.checkLtime(ltime,vtime);
        if(b)
            return MsgUtil.success();
        else
            return MsgUtil.error();
    }


    //检验来访时间
    @ResponseBody
    @RequestMapping("checkvtime")
    public MsgUtil checkVTime(@RequestParam("vtime") String vtime) throws ParseException {
        boolean b =  visitService.checkVtime(vtime);
        if(b)
            return MsgUtil.success();
        else
            return MsgUtil.error();
    }



    //校验学生名是否存在
    @ResponseBody
    @RequestMapping("/checkstudent")
    public MsgUtil checkStudent(@RequestParam("sname") String sname){
        boolean b =  visitService.checkStudent(sname);
        if(b)
            return MsgUtil.error();
        else
            return MsgUtil.success();
    }

    //返回记录编号
    @ResponseBody
    @RequestMapping("/findId")
    public int findId(@RequestParam("sname") String sname,@RequestParam("vtime") String vtime) throws ParseException {
        int id = visitService.findId(sname,vtime);
        return id;
    }

    //查询当前宿舍楼的所有来访信息
    @GetMapping("/visittable")
    public String getAllVisitOfAdminId(@RequestParam(value = "pn",defaultValue = "1")int pn,HttpSession session,Model model){
        floorNumber = (int) session.getAttribute("floorNumber");
        //传入页码，以及每页数
        PageHelper.startPage(pn,10);
        List<VisitInfo> list =  visitService.getAllVisitOfFloorNumber(floorNumber);
        //连续显示5页PageInfo(list,5);
        PageInfo page = new PageInfo(list);
        model.addAttribute("visitlist",page);
        return "admin/visittable";
    }

    //添加来访信息
    @PostMapping("/visit")
    public String save(VisitInfo visit, String vtime,String ltime) throws ParseException {
        visitService.save(visit,floorNumber,vtime,ltime);
        return "redirect:/visittable";
    }

    //修改来访信息
    @PutMapping("/visit")
    public String updateVisitInfo(VisitInfo visit,String vtime,String ltime) throws ParseException {
        visitService.update(visit,vtime,ltime);
        return "redirect:/visittable";
    }

    //删除来访信息
    @DeleteMapping("/visit/{id}")
    public String deleteVisit(@PathVariable("id") int id){
        visitService.delete(id);
        return "redirect:/visittable";
    }


}

package com.example.demo.controller;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.service.BoardMsgService;
import com.example.demo.service.CommentsService;
import com.example.demo.utils.MsgUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class BoardMsgController {
    @Autowired
    BoardMsgService boardMsgService;
    @Autowired
    CommentsService commentsService;

    //获得该楼所有留言
    @GetMapping("/msgboard")
    public String getAllBoardMsg(@RequestParam(value = "pn",defaultValue = "1")int pn, HttpSession session, Model model){
        int floorNumber;
        Person person = (Person)session.getAttribute("loginuser");
        int peopleId = person.getId();
        char id = (peopleId+"").charAt(0);
        if(id=='2'){
            floorNumber = (int)session.getAttribute("floorNumber");
        }else {
            Student student = (Student)session.getAttribute("loginuser");
            floorNumber = student.getFloorNumber();
        }
        PageHelper.startPage(pn,10);
        List<Map<String, Object>> allMsgForPerson = boardMsgService.getAllMsgForPerson(floorNumber,peopleId);
        Map<Integer,Integer> map =  boardMsgService.getAllStudentDorNumber(floorNumber);
        //获取所有评论信息
        List<Comments> commentsMap = commentsService.getAllComWithFloorNumber(floorNumber);
        Map<Integer,String> nameIdMap = boardMsgService.getAllPersonId(floorNumber);

        //将session中的消息提示重置
        Map<Integer,Integer> notificationMap =  commentsService.getNotificationWithPeopleId(peopleId);
        session.setAttribute("notification",notificationMap);
        PageInfo pageInfo = new PageInfo(allMsgForPerson);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("studentDor",map);
        model.addAttribute("comments",commentsMap);
        model.addAttribute("nameIdMap",nameIdMap);
        if(id=='2') {
            return "admin/msgboard";
        }else{
            return "student/msgboard";
        }
    }

    @ResponseBody
    @RequestMapping("/msgborad")
    public MsgUtil saveBoardMsg(HttpSession session, @RequestParam("title") String title,
                                @RequestParam("message") String message){
        Person person = (Person)session.getAttribute("loginuser");
        int id = person.getId();
        boardMsgService.saveBoardMsg(id,title,message);
        return MsgUtil.success();
    }

    @DeleteMapping("/msgborad/{id}")
    public String deleteMsgBorad(@PathVariable("id") int id){
        boardMsgService.delete(id);
        return "redirect:/msgboard";
    }


    /**
     * 保存评论信息
     * @param session
     * @param comment
     * @param msgId
     * @return
     */
    @ResponseBody
    @RequestMapping("/comments")
    public MsgUtil save(HttpSession session, @RequestParam("comment") String comment, @RequestParam("msgid") int msgId){
        Person person = (Person)session.getAttribute("loginuser");
        int id = person.getId();
        commentsService.save(id,comment,msgId);
        return MsgUtil.success() ;
    }

    /**
     * 删除评论
     * @param id    评论id
     * @return
     */
    @DeleteMapping("/comment/{id}")
    public String delete(@PathVariable("id") int id){
        commentsService.delete(id);
        return "redirect:/msgboard";
    }
}

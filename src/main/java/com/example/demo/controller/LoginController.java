package com.example.demo.controller;

import com.example.demo.entity.Identity;
import com.example.demo.entity.Person;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.AdminService;
import com.example.demo.service.CommentsService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource
    AdminService adminService;

    @Resource
    PersonService personService;

    @Resource
    CommentsService commentsService;


    @PostMapping(value = "/login")  //等价于下面的注解
    public String login(String username,
                        String password,
                        Model model,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) throws ServletException, IOException {
        List<Identity> all = personService.getAllIdentity();

        for (Identity identity : all) {
            if (username.equals(String.valueOf(identity.getName())) && password.equals(identity.getPassword())) {
                String level = String.valueOf(identity.getLevel());
                Person person = personService.getPersonForId(identity.getName());
                session.setAttribute("loginuser",person);
                //获得消息提示
                Map<Integer,Integer> map = commentsService.getNotificationWithPeopleId(person.getId());
                session.setAttribute("notification",map);
                if("2".equals(level)){
                    int floorNumber = adminService.getFloorNumberWithAdminId(identity.getName());
                    session.setAttribute("floorNumber",floorNumber);
                }
                return "redirect:/main" + level + ".html";
            }
        }
        redirectAttributes.addFlashAttribute("msg","用户名或密码错误");
        return "redirect:/index.html";
    }

}

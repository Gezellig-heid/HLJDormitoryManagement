package com.example.demo.service.impl;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.CommentsDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.service.PersonService;
import com.example.demo.utils.ImgUtil;
import com.example.demo.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    PersonDao personDao;

    @Resource
    AdminDao adminDao;

    @Resource
    StudentDao studentDao;

    @Resource
    CommentsDao commentsDao;


    @Override
    public MsgUtil updateAdminInfo(MultipartFile img, Admin admin) {
        String oldImgPath = admin.getImgPath();
        admin.setPhoneNumber(admin.getPhoneNumber().replace("-",""));
        String fileName = img.getOriginalFilename();    //获得图片名称(可能是空)
        String avatars;
        if(!StringUtils.isEmpty((fileName))){
            avatars = ImgUtil.saveImg(img,fileName);
            adminDao.updateAdminImg(admin.getId(),avatars);
        }
        personDao.updateAdminInfo(admin);
        //保存新信息
        if(!StringUtils.isEmpty(oldImgPath)){
            //删除原来的图片
            new File(ImgUtil.IMG_PATH_PREFIX+"/"+oldImgPath).delete();
        }
        return MsgUtil.success();
    }

    @Override
    public MsgUtil updateStudentInfo(MultipartFile img, Student student) {
        String oldImgPath = student.getImgPath();
        student.setPhoneNumber(student.getPhoneNumber().replace("-",""));
        String fileName = img.getOriginalFilename();    //获得图片名称(可能是空)
        String avatars;
        if(!StringUtils.isEmpty((fileName))){
            avatars = ImgUtil.saveImg(img,fileName);
            studentDao.updateStudentImg(student.getId(),avatars);
        }
        personDao.updateStudentInfo(student);
        //保存新信息
        if(!StringUtils.isEmpty(oldImgPath)){
            //删除原来的图片
            new File(ImgUtil.IMG_PATH_PREFIX+"/"+oldImgPath).delete();
        }
        return MsgUtil.success();
    }

    //修改密码(并且修改提示信息)
    @Override
    public void changePassword(int password1, String id) {
        personDao.changePassword(password1,id);
        int peopleId = Integer.valueOf(id);
        commentsDao.deleteNotification(peopleId,2);
    }

    @Override
    public Person getPersonForId(int id) {
        Person person = adminDao.getAdminWithId(id);
        if(person==null){
           person =  studentDao.getStudentWithId(id);
        }
        return person;
    }

    @Override
    public List<Identity> getAllIdentity() {
        return personDao.getAllIdentity();
    }



    @Override
    public Person findInfo(int id) {
        String sid = id+"";
        char level = sid.charAt(0);
        Person person;
        if(level == '1'){
            person =  personDao.findInfoFromAdmin(id);
        }else if(level == '2'){
            person = personDao.findInfoFromAdmin(id);
        }else{
            person = personDao.findInfoFromStudent(id);
        }
        return person;
    }
}

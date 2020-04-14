package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.utils.MsgUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonService {
    Person findInfo(int id);

    MsgUtil updateAdminInfo(MultipartFile img, Admin admin);

    void changePassword(int password1, String id);

    List<Identity> getAllIdentity();

    Person getPersonForId(int id);

    MsgUtil updateStudentInfo(MultipartFile img, Student student);
}

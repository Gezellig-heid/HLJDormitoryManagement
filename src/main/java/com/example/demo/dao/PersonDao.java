package com.example.demo.dao;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao {


    @Autowired
    PersonMapper personMapper;

    public Admin findInfoFromAdmin(int id) {
        Admin admin = personMapper.findInfoFromAdmin(id);
        return admin;
    }

    public Student findInfoFromStudent(int id) {
        Student student = personMapper.findInfoFromStudent(id);
        return student;
    }

    public void updateAdminInfo(Admin admin) {
        personMapper.updateAdminInfo(admin);
    }

    public void changePassword(int password1, String id) {
        personMapper.changePassword(password1,id);
    }

    //添加一个身份信息
    public void addIdentity(Person people) {
        String level = people.getId()+"";
        Identity identity = new Identity();
        identity.setUsername(people.getName());
        identity.setName(people.getId());
        identity.setPassword("123456");     //默认密码
        identity.setLevel(Integer.valueOf(String.valueOf(level.charAt(0))));
        personMapper.addIdentity(identity);
    }

    public void deleteAdmin(int id) {
        personMapper.deleteAdmin(id);
    }

    public List<Identity> getAllIdentity() {
        return personMapper.getAllIdentity();
    }

    public void updateStudentInfo(Student student) {
        personMapper.updateStudentInfo(student);
    }
}

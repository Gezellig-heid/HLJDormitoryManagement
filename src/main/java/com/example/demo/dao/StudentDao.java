package com.example.demo.dao;

import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StudentDao {
    @Resource
    StudentMapper studentMapper;

    @Resource
    AdminMapper adminMapper;

    //获取该宿舍楼下的所有学生
    public List<Student> getStudentsWithFloorNumber(int floorNumber) {
        List<Student> list = studentMapper.getStudentsWithFloorNumber(floorNumber);
        return list;
    }

    //添加学生信息和所在宿舍楼和宿舍信息
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    //根据姓名查询是否存在该学生
    public int countByName(String sname) {
        return studentMapper.countByName(sname);
    }

    //查询当前楼的所有学生和他们所在寝室的信息
    public List<Student> getAllStudentDorNumberAndMsg(int floorNumber) {
        List<Student> list = studentMapper.getAllStudentDorNumberAndMsg(floorNumber);
        return list;
    }

    //获取学生信息
    public Student getStudentWithId(int id) {
        return studentMapper.getStudentWithId(id);
    }


    //更新学生信息
    public void update(Student student) {
        studentMapper.update(student);
    }

    public void delete(int id) {
        studentMapper.delete(id);
    }

    public int getFloorNumberWithId(int id) {
        return studentMapper.getFloorNumberWithId(id);
    }

    public List<String> getAllCounselor(int floorNumber) {
        return studentMapper.getAllCounselor(floorNumber);
    }

    //获取该辅导员的学生总数
    public List<Student> getSumStudentsWithCounselor(int floorNumber, String counselor) {
        return studentMapper.getSumStudentsWithCounselor(floorNumber,counselor);
    }

    //设置他们学生的签到标志位
    public void setSignInStatus(int floorNumber, String counselor, int status) {
        studentMapper.setSignInStatus(floorNumber,counselor,status);
    }

    public List<Student> getStudentsWithSignInStatus(int floorNumber, String counselor, int status) {
        return studentMapper.getStudentsWithSignInStatus(floorNumber,counselor,status);
    }

    public void resetSignInStatus(Integer i) {
        studentMapper.resetSignInStatus(i);
    }

    public void updateStudentImg(int id, String avatars) {
        studentMapper.updateStudentImg(id,avatars);
    }

    public List<Student> getStudentsWithDorNumber(int floorNumber, int dorNumber) {
        return studentMapper.getStudentsWithDorNumber(floorNumber,dorNumber);
    }
}

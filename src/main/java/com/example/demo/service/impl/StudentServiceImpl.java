package com.example.demo.service.impl;

import com.example.demo.component.MyApplicationEvent;
import com.example.demo.dao.DormitoryDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ImgUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    ApplicationContext applicationContext;

    @Resource
    StudentDao studentDao;

    @Resource
    PersonDao personDao;

    @Resource
    DormitoryDao dormitoryDao;

    //获取该宿舍楼下的所有学生
    @Override
    public List<Student> getStudentsWithFloorNumber(int floorNumber) {
        return studentDao.getStudentsWithFloorNumber(floorNumber);
    }


    //删除学生信息
    @Override
    public void delete(int id) {
        studentDao.delete(id);
    }

    //更新学生信息
    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void addStudent(Student student) {
        if(StringUtils.isEmpty(student.getImgPath())){
            student.setImgPath(ImgUtil.DEFAULT_IMG_PATH);
        }
        studentDao.addStudent(student);
        personDao.addIdentity(student);
        dormitoryDao.dorAddPeople(student.getDorNumber(),student.getFloorNumber());
        //添加密码提示
        applicationContext.publishEvent(new MyApplicationEvent(new Object(),student.getId(),2));
    }

    @Override
    public Map<Integer, Student> getIdAndName(int floorNumber) {
        List<Student> students = studentDao.getStudentsWithFloorNumber(floorNumber);
        Map<Integer, Student> map = new HashMap<>();
        for (Student s : students) {
            map.put(s.getId(),s);
        }
        return map;
    }

    //获取当前楼的所有辅导员信息
    @Override
    public List<String> getAllCounselor(int floorNumber) {
        return studentDao.getAllCounselor(floorNumber);
    }

    @Override
    public List<Student> getStudentsWithSignInStatus(int floorNumber, String counselor, int status) {
        return studentDao.getStudentsWithSignInStatus(floorNumber,counselor,status);
    }

    @Override
    public Student getStudentWithId(int id) {
        return studentDao.getStudentWithId(id);
    }
}

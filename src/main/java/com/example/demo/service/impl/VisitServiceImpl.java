package com.example.demo.service.impl;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.VisitDao;
import com.example.demo.entity.Student;
import com.example.demo.entity.VisitInfo;
import com.example.demo.service.VisitService;
import com.example.demo.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Resource
    VisitDao visitDao;
    @Resource
    StudentDao studentDao;



    //查询该宿舍楼的来访信息（近一个星期）
    @Override
    public List<VisitInfo> getAllVisitOfFloorNumber(int floorNumber) {
        Date before7days = DateUtil.getDate(-7);
        List<VisitInfo> visitInfoList = visitDao.getAllVisitOfFloorNum(floorNumber,before7days);
        return visitInfoList;
    }


    /**
     * 检验来访时间
     * @param vtime 来访时间
     */
    @Override
    public Boolean checkVtime(String vtime) throws ParseException {
        vtime.replace("T"," ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(vtime);
        Date before7days = DateUtil.getDate(-7);
        if(parse.before(before7days)){
            return false;
        }
        return true;
    }

    /**
     * 检验离开时间
     * @param ltime 离开时间
     * @param vtime 来访时间
     * @return
     */
    @Override
    public boolean checkLtime(String ltime, String vtime) throws ParseException {
        vtime.replace("T"," ");
        ltime.replace("T"," ");
        if(StringUtils.isEmpty(ltime))
            return true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(ltime);
        Date parse1 = sdf.parse(vtime);
        if(parse.before(parse1))
            return false;
        return true;
    }

    /**
     * 根据姓名检查学生是否存在
     * @param '学生姓名'
     * @return true存在，false不存在
     */
    @Override
    public boolean checkStudent(String sname) {
        int i = studentDao.countByName(sname);
        return i==0;
    }

    /**
     *
     * @param sname
     * @param vtime
     * @return
     */
    @Override
    public int findId(String sname, String vtime) throws ParseException {
        vtime += ":00";
        int id = visitDao.findId(sname,vtime);
        return id;
    }

    /**
     * 删除来访记录
     * @param id 来访记录id
     */
    @Override
    public void delete(int id) {
        visitDao.delete(id);
    }

    /**
     * 更新来访记录
     * @param '来访信息'
     * @param vtime
     * @param ltime
     */
    @Override
    public void update(VisitInfo visit, String vtime, String ltime) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(!StringUtils.isEmpty(ltime)) {
            ltime = ltime.replace("T", " ");
            Date ldate = sf.parse(ltime);
            visit.setLtime(ldate);
        }
        //判断是离去修改还是更新修改
        if(StringUtils.isEmpty(visit.getVname())){
            //离去修改
            visitDao.leave(visit);
        }else{
            //普通更新
            vtime = vtime.replace("T"," ");
            Date vdate = sf.parse(vtime);
            visit.setVtime(vdate);
            visitDao.update(visit);
        }
    }

    //保存来访信息
    @Override
    public void save(VisitInfo visit, int floorNumber, String vtime, String ltime) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Student> students = studentDao.getStudentsWithFloorNumber(floorNumber);
        visit.setFloorNumber(floorNumber);
        for (Student s:students) {
            if(s.getName().equals(visit.getSname())) {
                visit.setSid(s.getId());
                break;
            }
        }
        vtime = vtime.replace("T", " ");
        Date vdate = sf.parse(vtime);
        visit.setVtime(vdate);
        if(!StringUtils.isEmpty(ltime)){
            //如果离开时间不是空
            ltime = ltime.replace("T"," ");
            Date ldate = sf.parse(ltime);
            visit.setLtime(ldate);
        }
        visitDao.save(visit);
    }

    @Override
    public List<Student> getAllStudentOfFloorNumber(int adminId) {
        List<Student> students = studentDao.getStudentsWithFloorNumber(adminId);
        return students;
    }
}

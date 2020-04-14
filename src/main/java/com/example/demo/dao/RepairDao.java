package com.example.demo.dao;

import com.example.demo.entity.Repair;
import com.example.demo.mapper.RepairMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Repair)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-11 09:58:00
 */
@Repository
public class RepairDao {
    @Resource
    RepairMapper repairMapper;

    public List<Repair> getAllRepair(int floorNumber) {
        return  repairMapper.getAllRepair(floorNumber);
    }

    public void changeStatus(int id, int status) {
        repairMapper.changeStatus(id,status);
    }

    //通过id找到该维修宿舍
    public int getDorNumberWithId(int id) {
        return repairMapper.getDorNumberWithId(id);
    }

    //通过id得到该宿舍楼
    public int getFloorNumberWithId(int id) {
        return repairMapper.getFloorNumberWithId(id);
    }


    //通过Id得到该学生
    public int getStudentIdWithId(int id) {
        return repairMapper.getStudentIdWithId(id);
    }

    public List<Repair> getDorRepair(int id) {
        return repairMapper.getDorRepair(id);
    }

    public void addDorRepair(Repair repair) {
        repairMapper.addDorRepair(repair);
    }
}
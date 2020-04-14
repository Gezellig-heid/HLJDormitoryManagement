package com.example.demo.dao;

import com.example.demo.entity.VisitInfo;
import com.example.demo.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class VisitDao {
    @Autowired
    VisitMapper visitMapper;

    public List<VisitInfo> getAllVisitOfFloorNum(int floorNum, Date before7days) {
        return visitMapper.getAllVisitOfFloorNum(floorNum,before7days);
    }

    public void save(VisitInfo visit) {
        visitMapper.save(visit);
    }

    public void leave(VisitInfo visit) {
        visitMapper.leave(visit);
    }

    public int findId(String sname, String vtime) {
        return visitMapper.findId(sname,vtime);
    }

    public void update(VisitInfo visit) {
        visitMapper.update(visit);
    }

    public void delete(int id) {
        visitMapper.delete(id);
    }
}

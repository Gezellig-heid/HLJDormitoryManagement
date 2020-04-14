package com.example.demo.dao;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Person;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class AdminDao {
    @Autowired
    AdminMapper adminMapper;

    public List<Admin> getAll() {
        List<Admin> list = adminMapper.getAll();
        return list;
    }

    public void addAdmin(Admin admin) {

        adminMapper.addAdmin(admin);
    }

    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    public void deleteAdmin(int id) {
        adminMapper.deleteAdmin(id);
    }

    public int getFloorNumberWithAdminId(int adminId){
        return adminMapper.getFloorNumberWithAdminId(adminId);
    }

    public List<Admin> getAdminWithFloorNumber(int floorNumber) {
        return adminMapper.getAdminWithFloorNumber(floorNumber);
    }

    public Admin getAdminWithId(int id) {
        return adminMapper.getAdminWithId(id);
    }


    public void updateAdminImg(int id, String avatars) {
        adminMapper.updateAdminImg(id,avatars);
    }
}

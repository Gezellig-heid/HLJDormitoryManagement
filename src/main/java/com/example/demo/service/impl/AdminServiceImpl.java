package com.example.demo.service.impl;

import com.example.demo.component.MyApplicationEvent;
import com.example.demo.dao.AdminDao;
import com.example.demo.dao.FloorInfoDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.utils.ImgUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;

    @Resource
    PersonDao personDao;


    @Resource
    ApplicationContext applicationContext;

    @Override
    public List<Admin> getAll() {
        return adminDao.getAll();
    }

    @Override
    public int getFloorNumberWithAdminId(int name) {
        return adminDao.getFloorNumberWithAdminId(name);
    }

    @Override
    public void addAdmin(Admin admin) {
        if(StringUtils.isEmpty(admin.getImgPath())){
            //设置默认头像
            admin.setImgPath(ImgUtil.DEFAULT_IMG_PATH);
        }
        //添加管理员
        adminDao.addAdmin(admin);
        //添加管理员身份
        personDao.addIdentity(admin);
        //添加密码提示
        applicationContext.publishEvent(new MyApplicationEvent(new Object(),admin.getId(),2));
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminDao.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(int id) {
        //删除管理员
        adminDao.deleteAdmin(id);
        //删除管理员身份
        personDao.deleteAdmin(id);
    }
}

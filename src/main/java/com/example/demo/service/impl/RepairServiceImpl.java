package com.example.demo.service.impl;

import com.example.demo.component.MyApplicationEvent;
import com.example.demo.dao.CostDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Repair;
import com.example.demo.dao.RepairDao;
import com.example.demo.entity.Student;
import com.example.demo.service.RepairService;
import com.example.demo.utils.ImgUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Repair)表服务实现类
 *
 * @author makejava
 * @since 2020-03-11 09:58:01
 */
@Service("repairService")
public class RepairServiceImpl implements RepairService {
    private final static int cost=30;       //默认30元

    @Resource
    private RepairDao repairDao;

    @Resource
    private CostDao costDao;

    @Resource
    ApplicationContext applicationContext;

    @Override
    public void changeStatus(int id, int status, int artificial) {
        if(status==2&&artificial==1) {
            //是人为
            int floorNumber = repairDao.getFloorNumberWithId(id);
            int dorNumber = repairDao.getDorNumberWithId(id);
            costDao.updateFixFee(floorNumber,dorNumber,cost);
            //提示需要上缴维修费
            int studentId = repairDao.getStudentIdWithId(id);
            applicationContext.publishEvent(new MyApplicationEvent(new Object(),studentId,4));
        }
        repairDao.changeStatus(id,status);
    }

    @Override
    public List<Repair> getAllRepair(int floorNumber) {
        return repairDao.getAllRepair(floorNumber);
    }

    //获取维修工单
    @Override
    public List<Repair> getDorRepair(int id) {
        return repairDao.getDorRepair(id);
    }

    //添加维修工单
    @Override
    public void addDorRepair(Student student, MultipartFile img, String note) {
        Repair repair = new Repair();
        repair.setStudentid(student.getId());
        repair.setFloornumber(student.getFloorNumber());
        repair.setStatus(1);
        repair.setNote(note);
        String fileName = img.getOriginalFilename();    //获得图片名称(可能是空)
        String repairImg = null;
        if(!StringUtils.isEmpty((fileName))){
            repairImg = ImgUtil.saveRepairImg(img,fileName);
        }
        repair.setImgpath(repairImg);
        repairDao.addDorRepair(repair);
    }
}
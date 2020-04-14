package com.example.demo.service;

import com.example.demo.entity.Repair;
import com.example.demo.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (Repair)表服务接口
 *
 * @author makejava
 * @since 2020-03-11 09:58:00
 */
public interface RepairService {

    List<Repair> getAllRepair(int floorNumber);

    void changeStatus(int id, int status, int artificial);

    List<Repair> getDorRepair(int id);

    void addDorRepair(Student student, MultipartFile img, String note);
}
package com.example.demo.service;

import com.example.demo.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAll();

    void addAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(int id);

    int getFloorNumberWithAdminId(int name);
}

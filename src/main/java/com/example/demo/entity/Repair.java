package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Repair)实体类
 *
 * @author makejava
 * @since 2020-03-11 09:58:00
 */
public class Repair implements Serializable {
    private static final long serialVersionUID = 358422765015701911L;
    
    private Integer id;

    private Integer studentid;
    
    private String note;
    
    private String imgpath;
    
    private Integer status;     //1待审核2已审核3已完成4已拒绝

    private Integer floornumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFloornumber() {
        return floornumber;
    }

    public void setFloornumber(Integer floornumber) {
        this.floornumber = floornumber;
    }

}
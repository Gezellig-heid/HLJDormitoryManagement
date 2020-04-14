package com.example.demo.entity;

import java.time.LocalTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (Sign)实体类
 *
 * @author makejava
 * @since 2020-03-14 10:08:14
 */
public class Sign implements Serializable {
    private static final long serialVersionUID = 481890998097404358L;
    
    private Integer id;
    
    private LocalTime starttime;
    
    private LocalTime endtime;
    
    private String counselor;
    
    private Integer checked;
    
    private Integer unchecked;

    private Integer floorNumber;

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getUnchecked() {
        return unchecked;
    }

    public void setUnchecked(Integer unchecked) {
        this.unchecked = unchecked;
    }

}
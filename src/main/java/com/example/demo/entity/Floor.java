package com.example.demo.entity;

public class Floor {
    private int floorNumber;        //楼号
    private String floorName;       //楼名
    private int dorNums;            //宿舍总数
    private String region;          //所在区域
    private int floorType;           //2、4、6、8人间

    public Floor() {
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getDorNums() {
        return dorNums;
    }

    public void setDorNums(int dorNums) {
        this.dorNums = dorNums;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getFloorType() {
        return floorType;
    }

    public void setFloorType(int floorType) {
        this.floorType = floorType;
    }
}

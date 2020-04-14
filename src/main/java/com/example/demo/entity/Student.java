package com.example.demo.entity;

public class Student extends Person{
    private int dorNumber;      //宿舍号
    private int floorNumber;    //宿舍楼号
    private String profession;  //专业
    private String counselor;   //辅导员
    private String sign;    //签到标志位(0是未开启签到、1是未签到、2是已签到)

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getDorNumber() {
        return dorNumber;
    }

    public void setDorNumber(int dorNumber) {
        this.dorNumber = dorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public Student() {
    }
}

package com.example.demo.entity;

import java.time.LocalDate;

public abstract class Person {
    private int id;     //学号（也是账号）
    private String name;//姓名
    private int sex;    //1代表男，2代表女
    private LocalDate birthday;     //出生日期
    private String phoneNumber;    //手机号
    private String imgPath;     //图片路径

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public Person() {
    }

    public Person(int id, String name, int sex, LocalDate birthday, String phoneNumber, String imgPath) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

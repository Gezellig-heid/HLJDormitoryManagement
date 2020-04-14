package com.example.demo.entity;


public class Identity {
    private String username;//用户名
    private int name;       //账号
    private String password;   //密码
    private int level;      //等级1、管理员2、老师3、学生

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", name=" + name +
                ", password=" + password +
                ", level=" + level +
                '}';
    }

    public Identity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Identity(String username, int name, String password, int level) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.level = level;
    }
}

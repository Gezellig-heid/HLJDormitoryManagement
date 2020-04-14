package com.example.demo.entity;


import java.util.Date;

public class BoardMsg {
    private int id;
    private int peopleId;
    private String title;       //标题
    private String message;     //主要内容
    private Date leaveDate;
    private String imgPath;     //图片路径

    @Override
    public String toString() {
        return "BoardMsg{" +
                "id=" + id +
                ", peopleId=" + peopleId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", leaveDate=" + leaveDate +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public BoardMsg() {
    }

    public BoardMsg(int id, int peopleId, String title, String message, Date leaveDate, String imgPath) {
        this.id = id;
        this.peopleId = peopleId;
        this.title = title;
        this.message = message;
        this.leaveDate = leaveDate;
        this.imgPath = imgPath;
    }
}

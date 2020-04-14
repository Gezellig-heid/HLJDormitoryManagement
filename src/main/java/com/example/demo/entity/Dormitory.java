package com.example.demo.entity;

//宿舍信息
public class Dormitory {
    private int dorNumber;      //宿舍号
    private int floorNumber;    //楼号
    private int people;         //人数
    private int roomType;    //房间类型(2,4,6)

    @Override
    public String toString() {
        return "DormitoryInfo{" +
                "dorNumber=" + dorNumber +
                ", floorNumber=" + floorNumber +
                ", people=" + people +
                ", roomType='" + roomType + '\'' +
                '}';
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

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }


    public Dormitory(int dorNumber, int floorNumber, int people, int roomType) {
        this.dorNumber = dorNumber;
        this.floorNumber = floorNumber;
        this.people = people;
        this.roomType = roomType;
    }

    public Dormitory() {
    }
}

package com.example.demo.entity;

public class Cost {
    private int id;
    private int dorNumber;
    private int floorNumber;    //楼号
    private double elecFee;     //电费
    private double fixFee;      //修理费用

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", dorNumber=" + dorNumber +
                ", floorNumber=" + floorNumber +
                ", elecFee=" + elecFee +
                ", fixFee=" + fixFee +
                '}';
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public double getFixFee() {
        return fixFee;
    }

    public void setFixFee(double fixFee) {
        this.fixFee = fixFee;
    }

    public Cost(int id, int dorNumber, int floorNumber, double elecFee, double fixFee) {
        this.id = id;
        this.dorNumber = dorNumber;
        this.floorNumber = floorNumber;
        this.elecFee = elecFee;
        this.fixFee = fixFee;
    }



    public Cost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return floorNumber;
    }

    public void setStudentId(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getDorNumber() {
        return dorNumber;
    }

    public void setDorNumber(int dorNumber) {
        this.dorNumber = dorNumber;
    }

    public double getElecFee() {
        return elecFee;
    }

    public void setElecFee(double elecFee) {
        this.elecFee = elecFee;
    }

}

package com.example.demo.entity;

import java.util.Date;

public class VisitInfo {
    private int id;
    private String vname;
    private int vsex;
    private int sid;
    private String sname;
    private int floorNumber;
    private String relationship;
    private Date vtime;
    private Date ltime;

    public VisitInfo(int id, String vname, int vsex, int sid, String sname, int floorNumber, String relationship, Date vtime, Date ltime) {
        this.id = id;
        this.vname = vname;
        this.vsex = vsex;
        this.sid = sid;
        this.sname = sname;
        this.floorNumber = floorNumber;
        this.relationship = relationship;
        this.vtime = vtime;
        this.ltime = ltime;
    }

    @Override
    public String toString() {
        return "VisitInfo{" +
                "id=" + id +
                ", vname='" + vname + '\'' +
                ", vsex=" + vsex +
                ", sid=" + sid +
                ", sname='" + sname + '\'' +
                ", floorNumber=" + floorNumber +
                ", relationship='" + relationship + '\'' +
                ", vtime=" + vtime +
                ", ltime=" + ltime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public int getVsex() {
        return vsex;
    }

    public void setVsex(int vsex) {
        this.vsex = vsex;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Date getVtime() {
        return vtime;
    }

    public void setVtime(Date vtime) {
        this.vtime = vtime;
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public VisitInfo() {
    }
}

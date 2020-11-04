package com.example.demo.domain;

import java.io.Serializable;

public class Vip implements Serializable {
    Integer vid;
    Integer pri;
    double money;
    Integer xid;

    public Integer getVid() {
        return vid;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "vid=" + vid +
                ", pri=" + pri +
                ", money=" + money +
                ", xid=" + xid +
                '}';
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }
}

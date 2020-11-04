package com.example.demo.domain;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer aid;
    private Integer uid;
    private  double money;

    public Integer getAid() {
        return aid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

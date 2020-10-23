package com.example.file.Entiy;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.Date;

public class People extends LitePalSupport {
    private int id;
    private String name;
    private int age;

    private boolean recoard;






    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", recoard=" + recoard +
                '}';
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public boolean isRecoard() {
        return recoard;
    }

    public void setRecoard(boolean recoard) {
        this.recoard = recoard;
    }
}

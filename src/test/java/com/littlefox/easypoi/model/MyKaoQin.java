package com.littlefox.easypoi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-09-04 18:04
 */
public class MyKaoQin {

    private String number;

    private String name;

    private String dept;

    private List<KaoQin> list = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<KaoQin> getList() {
        return list;
    }

    public void setList(List<KaoQin> list) {
        this.list = list;
    }


    public void add(KaoQin kaoQin){
        list.add(kaoQin);
    }

    @Override
    public String toString() {
        return "MyKaoQin{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", list=" + list +
                '}';
    }
}

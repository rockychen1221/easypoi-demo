package com.littlefox.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-06-17 21:06
 */
public class YearDataModel implements Serializable {
    /**
     * 期初余额
     */
    @Excel(name = "期初余额")
    private String        beginName;
    /**
     * 期末余额
     */
    @Excel(name = "期末余额")
    private String           endName;

    public String getBeginName() {
        return beginName;
    }

    public void setBeginName(String beginName) {
        this.beginName = beginName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }
}

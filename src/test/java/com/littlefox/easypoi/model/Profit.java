package com.littlefox.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 利润
 * @author rockychen
 * @version 1.0
 * @date 2020-06-22 12:37
 */
public class Profit {

    @Excel(name = "项目")
    private String subject;

    @Excel(name = "本期余额")
    private String currentAmount;

    @Excel(name = "本年余额")
    private String amountYear;

    @Excel(name = "备注")
    private String remark;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getAmountYear() {
        return amountYear;
    }

    public void setAmountYear(String amountYear) {
        this.amountYear = amountYear;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

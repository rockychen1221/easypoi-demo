package com.littlefox.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 资产负债
 * @author rockychen
 * @version 1.0
 * @date 2020-06-22 12:37
 */
public class AssetsLiabilities {

    @Excel(name = "资产")
    private String assets;

    @Excel(name = "年初余额", fixedIndex=1)
    private String initialBalance;

    @Excel(name = "期末余额", fixedIndex=2)
    private String endingBalance;

    @Excel(name = "备注", fixedIndex=3)
    private String remark;

    @Excel(name = "负债和所有者权益")
    private String ownersEequity;

    @Excel(name = "年初余额", fixedIndex=5)
    private String initialBalance2;

    @Excel(name = "期末余额", fixedIndex=6)
    private String endingBalance2;

    @Excel(name = "备注", fixedIndex=7)
    private String remark2;

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOwnersEequity() {
        return ownersEequity;
    }

    public void setOwnersEequity(String ownersEequity) {
        this.ownersEequity = ownersEequity;
    }

    public String getInitialBalance2() {
        return initialBalance2;
    }

    public void setInitialBalance2(String initialBalance2) {
        this.initialBalance2 = initialBalance2;
    }

    public String getEndingBalance2() {
        return endingBalance2;
    }

    public void setEndingBalance2(String endingBalance2) {
        this.endingBalance2 = endingBalance2;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
}

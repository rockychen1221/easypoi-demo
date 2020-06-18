package com.littlefox.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-06-17 21:06
 */
public class FinanceModel implements Serializable {

    @Excel(name = "科目")
    private String subject;

    @ExcelCollection(name = "2020")
    private List<YearDataModel> year2020 = Lists.newArrayList();

    @ExcelCollection(name = "2021")
    private List<YearDataModel> year2021 = Lists.newArrayList();

    @ExcelCollection(name = "2022")
    private List<YearDataModel> year2022 = Lists.newArrayList();


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<YearDataModel> getYear2020() {
        return year2020;
    }

    public void setYear2020(List<YearDataModel> year2020) {
        this.year2020 = year2020;
    }

    public List<YearDataModel> getYear2021() {
        return year2021;
    }

    public void setYear2021(List<YearDataModel> year2021) {
        this.year2021 = year2021;
    }

    public List<YearDataModel> getYear2022() {
        return year2022;
    }

    public void setYear2022(List<YearDataModel> year2022) {
        this.year2022 = year2022;
    }
}

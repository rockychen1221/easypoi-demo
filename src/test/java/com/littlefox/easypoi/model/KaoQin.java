package com.littlefox.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.time.LocalDate;
import java.util.stream.Collector;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-09-04 16:35
 */
public class KaoQin {

    @Excel(name = "1", fixedIndex = 1)
    private String d1;
    @Excel(name = "1", fixedIndex = 2)
    private String d2;
    @Excel(name = "1", fixedIndex = 3)
    private String d3;
    @Excel(name = "1", fixedIndex = 4)
    private String d4;
    @Excel(name = "1", fixedIndex = 5)
    private String d5;
    @Excel(name = "1", fixedIndex = 6)
    private String d6;
    @Excel(name = "1", fixedIndex = 7)
    private String d7;
    @Excel(name = "1", fixedIndex = 8)
    private String d8;
    @Excel(name = "1", fixedIndex = 9)
    private String d9;
    @Excel(name = "1", fixedIndex = 10)
    private String d10;
    @Excel(name = "1", fixedIndex = 11)
    private String d11;
    @Excel(name = "1", fixedIndex = 12)
    private String d12;
    @Excel(name = "1", fixedIndex = 13)
    private String d13;
    @Excel(name = "1", fixedIndex = 14)
    private String d14;
    @Excel(name = "1", fixedIndex = 15)
    private String d15;
    @Excel(name = "1", fixedIndex = 16)
    private String d16;
    @Excel(name = "1", fixedIndex = 17)
    private String d17;
    @Excel(name = "1", fixedIndex = 18)
    private String d18;
    @Excel(name = "1", fixedIndex = 19)
    private String d19;
    @Excel(name = "1", fixedIndex = 20)
    private String d20;

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getD5() {
        return d5;
    }

    public void setD5(String d5) {
        this.d5 = d5;
    }

    public String getD6() {
        return d6;
    }

    public void setD6(String d6) {
        this.d6 = d6;
    }

    public String getD7() {
        return d7;
    }

    public void setD7(String d7) {
        this.d7 = d7;
    }

    public String getD8() {
        return d8;
    }

    public void setD8(String d8) {
        this.d8 = d8;
    }

    public String getD9() {
        return d9;
    }

    public void setD9(String d9) {
        this.d9 = d9;
    }

    public String getD10() {
        return d10;
    }

    public void setD10(String d10) {
        this.d10 = d10;
    }

    public String getD11() {
        return d11;
    }

    public void setD11(String d11) {
        this.d11 = d11;
    }

    public String getD12() {
        return d12;
    }

    public void setD12(String d12) {
        this.d12 = d12;
    }

    public String getD13() {
        return d13;
    }

    public void setD13(String d13) {
        this.d13 = d13;
    }

    public String getD14() {
        return d14;
    }

    public void setD14(String d14) {
        this.d14 = d14;
    }

    public String getD15() {
        return d15;
    }

    public void setD15(String d15) {
        this.d15 = d15;
    }

    public String getD16() {
        return d16;
    }

    public void setD16(String d16) {
        this.d16 = d16;
    }

    public String getD17() {
        return d17;
    }

    public void setD17(String d17) {
        this.d17 = d17;
    }

    public String getD18() {
        return d18;
    }

    public void setD18(String d18) {
        this.d18 = d18;
    }

    public String getD19() {
        return d19;
    }

    public void setD19(String d19) {
        this.d19 = d19;
    }

    public String getD20() {
        return d20;
    }

    public void setD20(String d20) {
        this.d20 = d20;
    }

    @Override
    public String toString() {
        return "KaoQing{" +
                "d1='" + d1 + '\'' +
                ", d2='" + d2 + '\'' +
                ", d3='" + d3 + '\'' +
                ", d4='" + d4 + '\'' +
                ", d5='" + d5 + '\'' +
                ", d6='" + d6 + '\'' +
                ", d7='" + d7 + '\'' +
                ", d8='" + d8 + '\'' +
                ", d9='" + d9 + '\'' +
                ", d10='" + d10 + '\'' +
                ", d11='" + d11 + '\'' +
                ", d12='" + d12 + '\'' +
                ", d13='" + d13 + '\'' +
                ", d14='" + d14 + '\'' +
                ", d15='" + d15 + '\'' +
                ", d16='" + d16 + '\'' +
                ", d17='" + d17 + '\'' +
                ", d18='" + d18 + '\'' +
                ", d19='" + d19 + '\'' +
                ", d20='" + d20 + '\'' +
                '}';
    }

//    public static Collector<KaoQin> collector() {
//        return Collector.of(
//                KaoQin::new,
//                KaoQin::accumulate,
//                KaoQin::combine,
//                Collector.Characteristics.IDENTITY_FINISH,
//                Collector.Characteristics.UNORDERED);
//    }
//
//    LocalDate start;
//    LocalDate end;
//    double minAmount = Double.POSITIVE_INFINITY;
//    double maxAmount = Double.NEGATIVE_INFINITY;
//
//    public void accumulate(final KaoQin tx) {
//        start = start != null && start.isBefore(tx.when) ? start : tx.when;
//        end = end != null && end.isAfter(tx.when) ? end : tx.when;
//        minAmount = Math.min(minAmount, tx.amount);
//        maxAmount = Math.max(maxAmount, tx.amount);
//
//    }
//
//    public KaoQin combine(final KaoQin other) {
//        start = start.isBefore(other.start) ? start : other.start;
//        end = end.isAfter(other.end) ? end : other.end;
//        minAmount = Math.min(minAmount, other.minAmount);
//        maxAmount = Math.max(maxAmount, other.maxAmount);
//        return this;
//    }
}

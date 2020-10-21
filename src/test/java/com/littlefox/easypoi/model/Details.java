package com.littlefox.easypoi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-10-21 14:41
 */
public class Details {

    final LocalDate date;
    final LocalTime time;

    public Details(String date, String time ) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time,fmt);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}

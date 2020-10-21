package com.littlefox.easypoi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-10-21 14:41
 */
public class Details {

    final LocalDateTime when;

    public Details(LocalDateTime when) {
        this.when = when;
    }

    public Details(String when) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm")
                //.parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        this.when = LocalDateTime.parse(when,fmt);
    }

    public LocalDateTime getWhen() {
        return when;
    }
}

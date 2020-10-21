package com.littlefox.easypoi.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.stream.Collector;

public class XirrDetails {

    public static Collector<Details, XirrDetails, XirrDetails> collector() {
        return Collector.of(
                XirrDetails::new,
                XirrDetails::accumulate,
                XirrDetails::combine,
                Collector.Characteristics.IDENTITY_FINISH,
                Collector.Characteristics.UNORDERED);
    }

    LocalTime start;
    LocalTime end;

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void accumulate(final Details tx) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        if (Duration.between(tx.getTime(), LocalTime.parse("00:00", fmt)).toSeconds() == 0) {
            return;
        }
        start = start != null && start.isBefore(tx.time) ? start : tx.time;
        end = end != null && end.isAfter(tx.time) ? end : tx.time;
    }

    public XirrDetails combine(final XirrDetails other) {
        start = start.isBefore(other.start) ? start : other.start;
        end = end.isAfter(other.end) ? end : other.end;
        return this;
    }

}

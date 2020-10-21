package com.littlefox.easypoi.model;

import java.time.LocalDateTime;
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

    LocalDateTime start;
    LocalDateTime end;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void accumulate(final Details tx) {
        start = start != null && start.isBefore(tx.when) ? start : tx.when;
        end = end != null && end.isAfter(tx.when) ? end : tx.when;
    }

    public XirrDetails combine(final XirrDetails other) {
        start = start.isBefore(other.start) ? start : other.start;
        end = end.isAfter(other.end) ? end : other.end;
        return this;
    }

    public void validate() {
        if (start == null) {
            throw new IllegalArgumentException("No transactions to anaylze");
        }

        if (start.equals(end)) {
            throw new IllegalArgumentException(
                "Transactions must not all be on the same day.");
        }
    }

}

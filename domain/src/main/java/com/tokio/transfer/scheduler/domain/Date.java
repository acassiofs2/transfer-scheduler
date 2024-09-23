package com.tokio.transfer.scheduler.domain;

import com.tokio.transfer.scheduler.domain.utils.DateUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Date extends ValueObject<LocalDate> {

    private final LocalDate value;

    private Date(LocalDate value) {
        this.value = value;
    }

    public static Date of(LocalDate date) {
        if (date == null) return null;
        return new Date(date);
    }

    public static Date of(String date) {
        if (date == null) return null;
        return new Date(DateUtils.of(date));
    }

    public static Date of(Instant date) {
        if (date == null) return null;
        return new Date(DateUtils.of(date));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getValue().format(formatter);
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(getValue(), date.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}

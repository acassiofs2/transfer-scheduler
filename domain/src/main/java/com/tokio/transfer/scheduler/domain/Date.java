package com.tokio.transfer.scheduler.domain;

import com.tokio.transfer.scheduler.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.Objects;

public class Date extends ValueObject<Instant> {

    private final Instant value;

    private Date(Instant value) {
        this.value = value;
    }

    public static Date of(String date) {
        if (date == null) return null;
        return new Date(InstantUtils.of(date));
    }

    @Override
    public Instant getValue() {
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

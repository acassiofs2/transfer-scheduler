package com.tokio.transfer.scheduler.domain;

import com.tokio.transfer.scheduler.domain.utils.InstantUtils;

import java.time.Instant;

public class Date extends ValueObject<Instant> {

    private final Instant value;

    private Date(Instant value) {
        this.value = value;
    }

    public static Date of(String date) {
        return new Date(InstantUtils.of(date));
    }

    @Override
    public Instant getValue() {
        return value;
    }
}

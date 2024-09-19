package com.tokio.transfer.scheduler.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Decimal extends ValueObject<BigDecimal> {

    private final BigDecimal value;
    private final String symbol;

    private Decimal(final BigDecimal value, final String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public static Decimal of(final Double aValue, final Integer aScale, final String aSymbol) {
        int scale = aScale != null && aScale >= 0 ? aScale : 2;
        final var value = BigDecimal.valueOf(aValue);
        return new Decimal(value.setScale(scale, RoundingMode.HALF_UP), aSymbol);
    }

    public static Decimal of(final Double aValue, final Integer aScale) {
        int scale = aScale != null && aScale >= 0 ? aScale : 2;
        final var value = BigDecimal.valueOf(aValue);
        return new Decimal(value.setScale(scale, RoundingMode.HALF_UP), null);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return symbol != null && !symbol.isEmpty() ? symbol + " " + value : value.toString();
    }
}

package com.tokio.transfer.scheduler.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

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
        if (aValue == null) return null;
        int scale = aScale != null && aScale >= 0 ? aScale : 2;
        final var value = BigDecimal.valueOf(aValue);
        return new Decimal(value.setScale(scale, RoundingMode.HALF_UP), null);
    }

    public static Decimal of(final BigDecimal aValue, final Integer aScale) {
        if (aValue == null) return null;
        int scale = aScale != null && aScale >= 0 ? aScale : 2;
        return new Decimal(aValue.setScale(scale, RoundingMode.HALF_UP), null);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        final var v = decimalFormat.format(value);
        return symbol != null && !symbol.isEmpty() ? symbol + " " + v : v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decimal decimal = (Decimal) o;
        return Objects.equals(getValue(), decimal.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}

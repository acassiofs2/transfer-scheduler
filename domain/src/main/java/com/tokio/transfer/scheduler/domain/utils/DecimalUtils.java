package com.tokio.transfer.scheduler.domain.utils;

import com.tokio.transfer.scheduler.domain.Decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class DecimalUtils {

    private DecimalUtils() {
    }

    public static Decimal calculatePercent(BigDecimal value, Double percentage) {
        BigDecimal result =
                value.multiply(
                        BigDecimal.valueOf(percentage)
                        .divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP)
                )
                .setScale(2, RoundingMode.HALF_UP);
        return Decimal.of(result, 1);
    }
}

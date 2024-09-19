package com.tokio.transfer.scheduler.domain.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public final class InstantUtils {

    private InstantUtils() {
    }

    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }

    private static Instant convertStringToInstant(String dateString) {
        if (dateString == null) return null;
        try {
            DateTimeFormatter formatter = null;
            if (dateString.matches("^\\d\\d/\\d\\d/\\d{2}$")) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            }
            if (dateString.matches("^\\d\\d/\\d\\d/\\d{4}$")) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            }
            if (dateString.matches("^\\d{2}-\\d\\d-\\d\\d$")) {
                formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            }
            if (dateString.matches("^\\d{4}-\\d\\d-\\d\\d$")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
            if (formatter != null) {
                LocalDateTime localDateTime = LocalDate.parse(dateString, formatter).atTime(0,0);
                return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            }
            throw new DateTimeParseException("","",0);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido: " + dateString);
            return null;
        }
    }

    public static Instant of(String value) {
        return convertStringToInstant(value);
    }
}

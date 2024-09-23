package com.tokio.transfer.scheduler.domain.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public final class DateUtils {

    private DateUtils() {
    }

    public static Instant nowInstant() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }

    public static LocalDate now() {
        return LocalDate.now(ZoneId.of("UTC-3"));
    }

    private static LocalDate convertStringToLocalDateTime(String dateString) {
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
                return LocalDate.parse(dateString, formatter);
            }
            throw new DateTimeParseException("","",0);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido: " + dateString);
            return null;
        }
    }

    public static LocalDate of(String value) {
        return convertStringToLocalDateTime(value);
    }

    public static LocalDate of(Instant value) {
        return LocalDate.ofInstant(value, ZoneId.of("UTC-3"));
    }
}

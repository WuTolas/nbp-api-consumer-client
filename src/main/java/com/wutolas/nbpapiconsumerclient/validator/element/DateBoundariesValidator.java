package com.wutolas.nbpapiconsumerclient.validator.element;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateBoundariesValidator {

    private final String datePattern;
    private final String dateMin;
    private final String dateMax;

    public DateBoundariesValidator(
            @Value("${api.nbp.date.pattern}") String datePattern,
            @Value("${api.nbp.date.min}") String dateMin,
            @Value("${api.nbp.date.max}") String dateMax
    ) {
        this.datePattern = datePattern;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
    }

    public boolean isValid(String dateString) {
        LocalDate providedDate;
        LocalDate minDate;
        LocalDate maxDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        try {
            providedDate = LocalDate.parse(dateString, formatter);
            minDate = LocalDate.parse(dateMin, formatter);

            if (dateMax.equals("now")) {
                maxDate = LocalDate.now();
            } else {
                maxDate = LocalDate.parse(dateMax, formatter);
            }

            return (providedDate.isAfter(minDate) && providedDate.isBefore(maxDate))
                    || providedDate.isEqual(minDate) || providedDate.isEqual(maxDate);

        } catch (DateTimeParseException ex) {
            return false;
        }

    }
}

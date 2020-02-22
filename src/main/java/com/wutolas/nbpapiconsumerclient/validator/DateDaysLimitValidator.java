package com.wutolas.nbpapiconsumerclient.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateDaysLimitValidator
        implements ConstraintValidator<DateDaysLimitConstraint, LocalDate> {

    private String datePattern;
    private String dateMax;
    private int daysLimit;

    @Override
    public boolean isValid(@NotNull LocalDate providedDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate maxDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        try {
            if (dateMax.equals("now")) {
                maxDate = LocalDate.now();
            } else {
                maxDate = LocalDate.parse(dateMax, formatter);
            }

            long days = ChronoUnit.DAYS.between(providedDate, maxDate);

            return days <= daysLimit;

        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    @Override
    public void initialize(DateDaysLimitConstraint constraintAnnotation) {
        datePattern = constraintAnnotation.datePattern();
        dateMax = constraintAnnotation.dateMax();
        daysLimit = constraintAnnotation.daysLimit();
    }
}

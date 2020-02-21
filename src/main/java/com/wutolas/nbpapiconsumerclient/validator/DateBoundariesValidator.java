package com.wutolas.nbpapiconsumerclient.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateBoundariesValidator implements ConstraintValidator<DateBoundariesConstraint, LocalDate> {

    private String datePattern;
    private String dateMin;
    private String dateMax;

    @Override
    public boolean isValid(@NotNull LocalDate providedDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate minDate;
        LocalDate maxDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        try {
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

    @Override
    public void initialize(DateBoundariesConstraint constraintAnnotation) {
        dateMin = constraintAnnotation.dateMin();
        dateMax = constraintAnnotation.dateMax();
        datePattern = constraintAnnotation.pattern();
    }

}

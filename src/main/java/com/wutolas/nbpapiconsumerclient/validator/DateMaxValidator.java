package com.wutolas.nbpapiconsumerclient.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateMaxValidator implements ConstraintValidator<DateMaxConstraint, LocalDate> {

    private String datePattern;
    private String dateMax;

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

            return providedDate.isBefore(maxDate) || providedDate.isEqual(maxDate);

        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    @Override
    public void initialize(DateMaxConstraint constraintAnnotation) {
        datePattern = constraintAnnotation.datePattern();
        dateMax = constraintAnnotation.dateMax();
    }

}

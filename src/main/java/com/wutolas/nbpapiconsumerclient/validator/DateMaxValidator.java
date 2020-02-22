package com.wutolas.nbpapiconsumerclient.validator;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateMaxValidator implements ConstraintValidator<DateMaxConstraint, LocalDate> {

    private final String datePattern;
    private String dateMax;

    public DateMaxValidator(@Value("${api.nbp.date.pattern}") String datePattern) {
        this.datePattern = datePattern;
    }

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
        dateMax = constraintAnnotation.dateMax();
    }

}

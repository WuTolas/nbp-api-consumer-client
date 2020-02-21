package com.wutolas.nbpapiconsumerclient.validator;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ApiNbpDateBoundariesValidator implements ConstraintValidator<ApiNbpDateBoundariesConstraint, LocalDate> {

    private final String datePattern;
    private final String dateMin;
    private final String dateMax;

    public ApiNbpDateBoundariesValidator(
            @Value("${api.nbp.date.pattern}") String datePattern,
            @Value("${api.nbp.date.min}") String dateMin,
            @Value("${api.nbp.date.max}") String dateMax) {
        this.datePattern = datePattern;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
    }

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
                    || providedDate.isEqual(minDate)
                    || providedDate.isEqual(maxDate);

        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    @Override
    public void initialize(ApiNbpDateBoundariesConstraint constraintAnnotation) {

    }

}

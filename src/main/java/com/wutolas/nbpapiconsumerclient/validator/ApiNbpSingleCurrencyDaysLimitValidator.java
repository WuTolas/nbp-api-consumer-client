package com.wutolas.nbpapiconsumerclient.validator;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ApiNbpSingleCurrencyDaysLimitValidator
        implements ConstraintValidator<ApiNbpSingleCurrencyDaysLimitConstraint, LocalDate> {

    private String datePattern;
    private String dateMax;
    private int daysLimit;

    public ApiNbpSingleCurrencyDaysLimitValidator(
            @Value("${api.nbp.date.pattern}") String datePattern,
            @Value("${api.nbp.date.max}") String dateMax,
            @Value("${api.nbp.days.singlecurrency.limit}") int daysLimit
    ) {
        this.datePattern = datePattern;
        this.dateMax = dateMax;
        this.daysLimit = daysLimit;
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

            long days = ChronoUnit.DAYS.between(providedDate, maxDate);

            return days <= daysLimit;

        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    @Override
    public void initialize(ApiNbpSingleCurrencyDaysLimitConstraint constraintAnnotation) {

    }
}

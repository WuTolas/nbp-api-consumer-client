package com.wutolas.nbpapiconsumerclient.validator.element;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateFormatValidator {

    private String datePattern;

    public DateFormatValidator(@Value("${api.nbp.date.pattern}") String datePattern) {
        this.datePattern = datePattern;
    }

    public boolean isValid(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

        try {
            formatter.parse(dateString);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}

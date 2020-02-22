package com.wutolas.nbpapiconsumerclient.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DateDaysLimitValidatorTest {

    private DateDaysLimitValidator validator;
    private int daysLimit = 10;
    private String dateMax = "2020-01-15";
    private String datePattern = "yyyy-MM-dd";

    @BeforeEach
    void setUp() {
        validator = new DateDaysLimitValidator(datePattern);
        Whitebox.setInternalState(validator, "dateMax", dateMax);
        Whitebox.setInternalState(validator, "daysLimit", daysLimit);
    }

    @Test
    void isValidShouldReturnTrueWhenDaysCountIsWithinLimit() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(datePattern)).minusDays(daysLimit);

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnFalseWhenDaysCountIsNotWithinLimit() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(datePattern)).minusDays(daysLimit+1);

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(false));
    }
}
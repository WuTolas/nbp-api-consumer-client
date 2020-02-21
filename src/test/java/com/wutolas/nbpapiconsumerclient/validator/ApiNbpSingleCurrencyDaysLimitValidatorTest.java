package com.wutolas.nbpapiconsumerclient.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ApiNbpSingleCurrencyDaysLimitValidatorTest {

    private ApiNbpSingleCurrencyDaysLimitValidator validator;
    private int daysLimit = 10;
    private String dateMax = "2020-01-15";
    private String pattern = "yyyy-MM-dd";

    @BeforeEach
    void setUp() {
        validator = new ApiNbpSingleCurrencyDaysLimitValidator(pattern, dateMax, daysLimit);
    }

    @Test
    void isValidShouldReturnTrueWhenDaysCountIsWithinLimit() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(pattern)).minusDays(daysLimit);

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnFalseWhenDaysCountIsNotWithinLimit() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(pattern)).minusDays(daysLimit+1);

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(false));
    }
}
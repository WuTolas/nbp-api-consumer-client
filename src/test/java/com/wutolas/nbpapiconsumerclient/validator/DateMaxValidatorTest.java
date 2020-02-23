package com.wutolas.nbpapiconsumerclient.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DateMaxValidatorTest {

    private DateMaxValidator validator;
    private String dateMax = "2012-12-12";
    private String datePattern = "yyyy-MM-dd";

    @BeforeEach
    void setUp() {
        validator = new DateMaxValidator();
        Whitebox.setInternalState(validator, "dateMax", dateMax);
        Whitebox.setInternalState(validator, "datePattern", datePattern);
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsBeforeMaxDate() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(datePattern)).minusDays(90);

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsEqualToMaxDate() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(datePattern));

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnFalseWhenProvidedDateIsAfterMaxDate() {
        //given
        LocalDate testedDate = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern(datePattern)).plusDays(1);

        //when
        boolean result = validator.isValid(testedDate, null);

        //then
        assertThat(result, is(false));
    }

}
package com.wutolas.nbpapiconsumerclient.validator.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DateBoundariesValidatorTest {

    private DateBoundariesValidator dateBoundariesValidator;
    private String dateMin = "2010-05-05";
    private String dateMax = "2012-12-12";

    @BeforeEach
    void setUp() {
        String pattern = "yyyy-MM-dd";
        dateBoundariesValidator = new DateBoundariesValidator(pattern, dateMin, dateMax);
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsWithinAllowedDateRange() {
        //given
        String testedDate = "2012-05-05";

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsEqualToMaxDate() {
        //given
        String testedDate = dateMax;

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsEqualToMinDate() {
        //given
        String testedDate = dateMin;

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnFalseWhenProvidedDateIsOutOfAllowedDateRange() {
        //given
        String testedDate = "1990-02-02";

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate);

        //then
        assertThat(result, is(false));
    }

    @Test
    void isValidShouldReturnFalseWhenProvidedDateIsUnparsable() {
        //given
        String testedDate = "19902-02";

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate);

        //then
        assertThat(result, is(false));
    }
}
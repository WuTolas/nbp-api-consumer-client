package com.wutolas.nbpapiconsumerclient.validator.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DateFormatValidatorTest {

    private DateFormatValidator dateFormatValidator;

    @BeforeEach
    void setUp() {
        String datePattern = "yyyy-MM-dd";
        dateFormatValidator = new DateFormatValidator(datePattern);
    }

    @Test
    void isValidShouldReturnTrueWhenDateStringIsParsable() {
        //given
        String date = "2005-02-05";

        //when
        boolean result = dateFormatValidator.isValid(date);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnFalseWhenDateStringContainsUnwantedCharacter() {
        //given
        String date = "2013-05-01s";

        //when
        boolean result = dateFormatValidator.isValid(date);

        //then
        assertThat(result, is(false));
    }

    @Test
    void isValidShouldReturnFalseWhenDateStringIsMissingLeadingZeroInDay() {
        //given
        String date = "2004-08-4";

        //when
        boolean result = dateFormatValidator.isValid(date);

        //then
        assertThat(result, is(false));
    }

    @Test
    void isValidShouldReturnFalseWhenDateStringIsMissingLeadingZeroInMonth() {
        //given
        String date = "2016-9-13";

        //when
        boolean result = dateFormatValidator.isValid(date);

        //then
        assertThat(result, is(false));
    }

    @Test
    void isValidShouldReturnFalseWhenDateStringIsIncorrect() {
        //given
        String date = "2004-13-32";

        //when
        boolean result = dateFormatValidator.isValid(date);

        //then
        assertThat(result, is(false));
    }
}
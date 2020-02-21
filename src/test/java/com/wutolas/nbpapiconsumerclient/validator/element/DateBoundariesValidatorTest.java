package com.wutolas.nbpapiconsumerclient.validator.element;

import com.wutolas.nbpapiconsumerclient.validator.DateBoundariesValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DateBoundariesValidatorTest {

    private DateBoundariesValidator dateBoundariesValidator;
    private String dateMin = "2010-05-05";
    private String dateMax = "2012-12-12";
    private String pattern = "yyyy-MM-dd";

    @BeforeEach
    void setUp() {
        dateBoundariesValidator = new DateBoundariesValidator();
        Whitebox.setInternalState(dateBoundariesValidator, "datePattern", pattern);
        Whitebox.setInternalState(dateBoundariesValidator, "dateMin", dateMin);
        Whitebox.setInternalState(dateBoundariesValidator, "dateMax", dateMax);
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsWithinAllowedDateRange() {
        //given
        String strDate = "2012-05-05";
        LocalDate testedDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(pattern));

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsEqualToMaxDate() {
        //given
        String strDate = dateMax;
        LocalDate testedDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(pattern));

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnTrueWhenProvidedDateIsEqualToMinDate() {
        //given
        String strDate = dateMin;
        LocalDate testedDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(pattern));

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate, null);

        //then
        assertThat(result, is(true));
    }

    @Test
    void isValidShouldReturnFalseWhenProvidedDateIsOutOfAllowedDateRange() {
        //given
        String strDate = "1990-02-02";
        LocalDate testedDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(pattern));

        //when
        boolean result = dateBoundariesValidator.isValid(testedDate, null);

        //then
        assertThat(result, is(false));
    }

}
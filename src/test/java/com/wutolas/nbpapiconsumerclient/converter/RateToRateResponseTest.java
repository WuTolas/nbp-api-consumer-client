package com.wutolas.nbpapiconsumerclient.converter;

import com.wutolas.nbpapiconsumerclient.model.api.Rate;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RateToRateResponseTest {

    private RateToRateResponse rateToRateResponse;

    @BeforeEach
    void setUp() {
        rateToRateResponse = new RateToRateResponse();
    }

    @Test
    void convertShouldReturnObjectWithSameValues() {
        //given
        Rate rate = new Rate();
        Whitebox.setInternalState(rate, "effectiveDate", LocalDate.now());
        Whitebox.setInternalState(rate, "bid", 50.5);
        Whitebox.setInternalState(rate, "ask", 50.25);

        //when
        RateResponse result = rateToRateResponse.convert(rate);

        //then
        assertNotNull(result);
        assertThat(result.getEffectiveDate(), is(rate.getEffectiveDate()));
        assertThat(result.getBid(), is(rate.getBid()));
        assertThat(result.getAsk(), is(rate.getAsk()));
    }
}
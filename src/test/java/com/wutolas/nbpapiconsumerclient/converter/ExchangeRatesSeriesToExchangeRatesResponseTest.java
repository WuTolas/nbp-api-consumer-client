package com.wutolas.nbpapiconsumerclient.converter;

import com.wutolas.nbpapiconsumerclient.model.api.ExchangeRatesSeries;
import com.wutolas.nbpapiconsumerclient.model.api.Rate;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class ExchangeRatesSeriesToExchangeRatesResponseTest {

    @Mock
    private RateToRateResponse rateToRateResponse;
    @InjectMocks
    private ExchangeRatesSeriesToExchangeRatesResponse exchangeRatesSeriesToExchangeRatesResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void convertShouldReturnObjectWithSameValues() {
        //given
        ExchangeRatesSeries exchangeRatesSeries = new ExchangeRatesSeries();
        Whitebox.setInternalState(exchangeRatesSeries, "currency", "Dolar");
        Whitebox.setInternalState(exchangeRatesSeries, "code", "USD");

        Rate rate = new Rate();
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        Whitebox.setInternalState(exchangeRatesSeries, "rates", rates);

        given(rateToRateResponse.convert(any(Rate.class))).willReturn(new RateResponse());

        //when
        ExchangeRatesResponse result = exchangeRatesSeriesToExchangeRatesResponse.convert(exchangeRatesSeries);

        //then
        assertNotNull(result);
        assertNotNull(result.getRates());
        assertThat(result.getRates().size(), is(rates.size()));
        assertThat(result.getCode(), is(exchangeRatesSeries.getCode()));
        assertThat(result.getCurrency(), is(exchangeRatesSeries.getCurrency()));
    }

}
package com.wutolas.nbpapiconsumerclient.service;

import com.wutolas.nbpapiconsumerclient.client.NbpExchangeRatesClient;
import com.wutolas.nbpapiconsumerclient.converter.ExchangeRatesSeriesToExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.model.api.ExchangeRatesSeries;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.util.RateResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class ExchangeRatesServiceImplTest {

    @Mock
    private NbpExchangeRatesClient nbpExchangeRatesClient;
    @Mock
    private ExchangeRatesSeriesToExchangeRatesResponse converter;
    @Mock
    private RateResponseUtil rateResponseUtil;
    @InjectMocks
    private ExchangeRatesServiceImpl exchangeRatesService;

    private String currency;
    private String dateFrom;
    private String dateTo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        //given
        currency = "usd";
        dateFrom = "2020-01-01";
        dateTo = "2020-02-02";
        ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse();
        exchangeRatesResponse.setRates(new LinkedList<>());

        given(nbpExchangeRatesClient.fetchBuyAndSellExchangeRatesByCurrencyAndDatesRange(anyString(), anyString(), anyString()))
                .willReturn(new ExchangeRatesSeries());
        given(converter.convert(ArgumentMatchers.any(ExchangeRatesSeries.class))).willReturn(new ExchangeRatesResponse());
    }

    @Test
    void getExchangeRatesWithDailyDifferencesShouldReturnExchangeRatesResponse() {
        //when
        ExchangeRatesResponse result = exchangeRatesService.getExchangeRatesWithDailyDifferences(
                currency, dateFrom, dateTo
        );

        //then
        assertNotNull(result);
    }

    @Test
    void getExchangeRatesWithDailyDifferencesShouldCallNbpExchangeRatesClientAndConverterAndCalculateBidAskDifferences() {
        //when
        ExchangeRatesResponse result = exchangeRatesService.getExchangeRatesWithDailyDifferences(
                currency, dateFrom, dateTo
        );

        //then
        then(nbpExchangeRatesClient)
                .should(times(1))
                .fetchBuyAndSellExchangeRatesByCurrencyAndDatesRange(anyString(), anyString(), anyString());
        then(converter)
                .should(times(1))
                .convert(ArgumentMatchers.any(ExchangeRatesSeries.class));
        then(rateResponseUtil)
                .should(times(1))
                .calculateBidAskDifferences(anyList());
    }
}
package com.wutolas.nbpapiconsumerclient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wutolas.nbpapiconsumerclient.client.NbpExchangeRatesClient;
import com.wutolas.nbpapiconsumerclient.converter.ExchangeRatesSeriesToExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.model.api.ExchangeRatesSeries;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.util.RateResponseUtil;
import org.springframework.stereotype.Service;


@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private static final Logger log = LogManager.getLogger(ExchangeRatesServiceImpl.class);
    private final NbpExchangeRatesClient nbpExchangeRatesClient;
    private final ExchangeRatesSeriesToExchangeRatesResponse exchangeRatesSeriesToExchangeRatesResponse;
    private final RateResponseUtil rateResponseUtil;

    public ExchangeRatesServiceImpl(
            NbpExchangeRatesClient nbpExchangeRatesClient,
            ExchangeRatesSeriesToExchangeRatesResponse exchangeRatesSeriesToExchangeRatesResponse,
            RateResponseUtil rateResponseUtil) {
        this.nbpExchangeRatesClient = nbpExchangeRatesClient;
        this.exchangeRatesSeriesToExchangeRatesResponse = exchangeRatesSeriesToExchangeRatesResponse;
        this.rateResponseUtil = rateResponseUtil;
    }

    @Override
    public ExchangeRatesResponse getExchangeRatesWithDailyDifferences(String currency, String dateFrom, String dateTo) {
        ExchangeRatesSeries exchangeRatesSeries = nbpExchangeRatesClient
                .fetchBuyAndSellExchangeRatesByCurrencyAndDatesRange(currency, dateFrom, dateTo);

        log.info("Successfully returned exchange rates by currency and dates range from the API");

        ExchangeRatesResponse exchangeRatesResponse = exchangeRatesSeriesToExchangeRatesResponse.convert(exchangeRatesSeries);
        rateResponseUtil.calculateBidAskDifferences(exchangeRatesResponse.getRates());

        return exchangeRatesResponse;
    }

}

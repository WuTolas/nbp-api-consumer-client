package com.wutolas.nbpapiconsumerclient.service;

import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;

public interface ExchangeRatesService {
    ExchangeRatesResponse getExchangeRatesWithDailyDifferences(String currency, String dateFrom, String dateTo);
}

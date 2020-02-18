package com.wutolas.nbpapiconsumerclient.service;

import com.wutolas.nbpapiconsumerclient.client.NbpExchangeRatesClient;
import com.wutolas.nbpapiconsumerclient.converter.ExchangeRatesSeriesToExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.helper.MathHelper;
import com.wutolas.nbpapiconsumerclient.model.api.ExchangeRatesSeries;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final NbpExchangeRatesClient nbpExchangeRatesClient;
    private final ExchangeRatesSeriesToExchangeRatesResponse exchangeRatesSeriesToExchangeRatesResponse;
    private final MathHelper mathHelper;

    public ExchangeRatesServiceImpl(
            NbpExchangeRatesClient nbpExchangeRatesClient,
            ExchangeRatesSeriesToExchangeRatesResponse exchangeRatesSeriesToExchangeRatesResponse,
            MathHelper mathHelper) {
        this.nbpExchangeRatesClient = nbpExchangeRatesClient;
        this.exchangeRatesSeriesToExchangeRatesResponse = exchangeRatesSeriesToExchangeRatesResponse;
        this.mathHelper = mathHelper;
    }

    @Override
    public ExchangeRatesResponse getExchangeRatesWithDailyDifferences(String currency, String dateFrom, String dateTo) {
        ExchangeRatesSeries exchangeRatesSeries = nbpExchangeRatesClient
                .fetchBuyAndSellExchangeRatesByCurrencyAndDatesRange(currency, dateFrom, dateTo);

        ExchangeRatesResponse exchangeRatesResponse = exchangeRatesSeriesToExchangeRatesResponse.convert(exchangeRatesSeries);
        calculateRateDifferences(exchangeRatesResponse.getRates());

        return exchangeRatesResponse;
    }

    private void calculateRateDifferences(List<RateResponse> rates) {
        double bidDifference;
        double askDifference;
        RateResponse previous = null;
        RateResponse current;
        ListIterator<RateResponse> listIterator = rates.listIterator();

        if (listIterator.hasNext()) {
            previous = listIterator.next();
        }

        while (listIterator.hasNext()) {
            current = listIterator.next();

            bidDifference = mathHelper.subtractBigDecimal(current.getBid(), previous.getBid(), 4);
            askDifference = mathHelper.subtractBigDecimal(current.getAsk(), previous.getAsk(), 4);
            current.setBidDifference(bidDifference);
            current.setAskDifference(askDifference);

            previous = current;
        }

    }

}

package com.wutolas.nbpapiconsumerclient.converter;

import com.wutolas.nbpapiconsumerclient.model.api.ExchangeRatesSeries;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRatesSeriesToExchangeRatesResponse implements Converter<ExchangeRatesSeries, ExchangeRatesResponse> {

    private final RateToRateResponse rateToRateResponse;

    public ExchangeRatesSeriesToExchangeRatesResponse(RateToRateResponse rateToRateResponse) {
        this.rateToRateResponse = rateToRateResponse;
    }

    @Override
    public ExchangeRatesResponse convert(@NonNull ExchangeRatesSeries source) {

        ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse();
        exchangeRatesResponse.setCode(source.getCode());
        exchangeRatesResponse.setCurrency(source.getCurrency());

        if (source.getRates() != null && source.getRates().size() > 0) {
            source.getRates().forEach(
                    rate -> exchangeRatesResponse.getRates().add(rateToRateResponse.convert(rate))
            );
        }

        return exchangeRatesResponse;
    }
}

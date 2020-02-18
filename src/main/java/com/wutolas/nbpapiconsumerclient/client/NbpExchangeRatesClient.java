package com.wutolas.nbpapiconsumerclient.client;

import com.wutolas.nbpapiconsumerclient.model.api.ExchangeRatesSeries;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "nbp-exchange-rates",
        url = "http://api.nbp.pl/api/exchangerates/")
public interface NbpExchangeRatesClient {

    @GetMapping(
            value = "/rates/c/{currency}/{dateFrom}/{dateTo}",
            produces = "application/json")
    ExchangeRatesSeries fetchBuyAndSellExchangeRatesByCurrencyAndDatesRange(
            @PathVariable String currency,
            @PathVariable String dateFrom,
            @PathVariable String dateTo);

}

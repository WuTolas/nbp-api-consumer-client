package com.wutolas.nbpapiconsumerclient.controller;

import com.wutolas.nbpapiconsumerclient.service.ExchangeRatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/{currency}/{dateFrom}/{dateTo}")
    public ResponseEntity<?> fetchRates(
            @PathVariable String currency,
            @PathVariable String dateFrom,
            @PathVariable String dateTo) {
        return ResponseEntity.ok(
                exchangeRatesService.getExchangeRatesWithDailyDifferences(currency, dateFrom, dateTo)
        );
    }

}

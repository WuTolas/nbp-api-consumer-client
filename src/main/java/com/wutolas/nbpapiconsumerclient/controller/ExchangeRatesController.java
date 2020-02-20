package com.wutolas.nbpapiconsumerclient.controller;

import com.wutolas.nbpapiconsumerclient.request.ExchangeRatesRequest;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.service.ExchangeRatesService;
import com.wutolas.nbpapiconsumerclient.validator.ExchangeRatesRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;
    private final ExchangeRatesRequestValidator exchangeRatesRequestValidator;

    public ExchangeRatesController(
            ExchangeRatesService exchangeRatesService,
            ExchangeRatesRequestValidator exchangeRatesRequestValidator
    ) {
        this.exchangeRatesService = exchangeRatesService;
        this.exchangeRatesRequestValidator = exchangeRatesRequestValidator;
    }

    @GetMapping("/usd")
    public ResponseEntity<?> fetchUsdRatesTillToday(
            ExchangeRatesRequest exchangeRatesRequest,
            BindingResult bindingResult,
            Model model
    ) {
        exchangeRatesRequestValidator.validate(exchangeRatesRequest, bindingResult);

        if (!bindingResult.hasErrors()) {
            String currency = "usd";

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strTodayDate = formatter.format(date);

            ExchangeRatesResponse exchangeRatesResponse = exchangeRatesService.getExchangeRatesWithDailyDifferences(
                    currency, exchangeRatesRequest.getDateFrom(), strTodayDate
            );

            model.addAttribute("exchangeRates", exchangeRatesResponse);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("not ok");
        }

    }

}

package com.wutolas.nbpapiconsumerclient.controller;

import com.wutolas.nbpapiconsumerclient.request.ExchangeRatesRequest;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.service.ExchangeRatesService;
import com.wutolas.nbpapiconsumerclient.validator.ExchangeRatesRequestValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;
    private final ExchangeRatesRequestValidator exchangeRatesRequestValidator;
    private final String datePattern;

    public ExchangeRatesController(
            ExchangeRatesService exchangeRatesService,
            ExchangeRatesRequestValidator exchangeRatesRequestValidator,
            @Value("${api.nbp.date.pattern}") String datePattern) {
        this.exchangeRatesService = exchangeRatesService;
        this.exchangeRatesRequestValidator = exchangeRatesRequestValidator;
        this.datePattern = datePattern;
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
            String strTodayDate = LocalDate.now().format(DateTimeFormatter.ofPattern(datePattern));

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

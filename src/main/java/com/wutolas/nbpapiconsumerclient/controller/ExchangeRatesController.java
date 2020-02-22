package com.wutolas.nbpapiconsumerclient.controller;

import com.wutolas.nbpapiconsumerclient.request.ExchangeRatesRequest;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.service.ExchangeRatesService;
import com.wutolas.nbpapiconsumerclient.validator.group.ExchangeRatesRequestOrderedChecks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/exchange-rates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/usd")
    public String fetchUsdRatesTillToday(
            @Validated(ExchangeRatesRequestOrderedChecks.class) ExchangeRatesRequest exchangeRatesRequest,
            BindingResult bindingResult,
            Model model
    ) {
        if (!bindingResult.hasErrors()) {
            String currency = "usd";
            String strTodayDate = LocalDate.now().toString();

            ExchangeRatesResponse exchangeRatesResponse = exchangeRatesService.getExchangeRatesWithDailyDifferences(
                    currency, exchangeRatesRequest.getDateFrom().toString(), strTodayDate
            );

            model.addAttribute("exchangeRatesResponse", exchangeRatesResponse);
        }

       return "exchangeRatesPage";
    }

    @GetMapping("/usd/new-search")
    public String usdRatesTillTodayInitForm(Model model) {
        model.addAttribute("exchangeRatesRequest", new ExchangeRatesRequest());
        return "exchangeRatesPage";
    }

}

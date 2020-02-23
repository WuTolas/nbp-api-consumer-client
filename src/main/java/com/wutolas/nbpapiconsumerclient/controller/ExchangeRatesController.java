package com.wutolas.nbpapiconsumerclient.controller;

import com.wutolas.nbpapiconsumerclient.request.ExchangeRatesRequest;
import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.service.ExchangeRatesService;
import com.wutolas.nbpapiconsumerclient.validator.group.ExchangeRatesRequestOrderedChecks;
import feign.FeignException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Locale;

@Controller
@RequestMapping("/exchange-rates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;
    private final MessageSource messageSource;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService, MessageSource messageSource) {
        this.exchangeRatesService = exchangeRatesService;
        this.messageSource = messageSource;
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

    @ExceptionHandler({ FeignException.NotFound.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String noDataAvailable(HttpServletRequest req, Model model) {
        ExchangeRatesRequest exchangeRatesRequest = new ExchangeRatesRequest();
        String date = req.getParameter("dateFrom");
        exchangeRatesRequest.setDateFrom(LocalDate.parse(date));

        model.addAttribute("exchangeRatesRequest", exchangeRatesRequest);
        model.addAttribute(
                "noDataAvailable",
                messageSource.getMessage("api.nbp.status.404", null, Locale.getDefault())
        );

        return "exchangeRatesPage";
    }
}

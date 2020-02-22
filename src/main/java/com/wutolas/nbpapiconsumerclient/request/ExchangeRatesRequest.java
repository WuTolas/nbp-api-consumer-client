package com.wutolas.nbpapiconsumerclient.request;

import com.wutolas.nbpapiconsumerclient.validator.DateMaxConstraint;
import com.wutolas.nbpapiconsumerclient.validator.DateDaysLimitConstraint;
import com.wutolas.nbpapiconsumerclient.validator.order.ExchangeRatesCheck;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ExchangeRatesRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{exchangeRatesRequest.dateFrom.NotNull}")
    @DateMaxConstraint(
            dateMax = "now",
            datePattern = "yyyy-MM-dd",
            message = "{exchangeRatesRequest.dateFrom.DateMax}",
            groups = ExchangeRatesCheck.class)
    @DateDaysLimitConstraint(
            daysLimit = 367,
            dateMax = "now",
            datePattern = "yyyy-MM-dd",
            message = "{exchangeRatesRequest.dateFrom.DateDaysLimitConstraint}",
            groups = ExchangeRatesCheck.class)
    private LocalDate dateFrom;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }
}

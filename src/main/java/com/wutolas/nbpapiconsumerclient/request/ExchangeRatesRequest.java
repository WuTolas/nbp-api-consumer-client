package com.wutolas.nbpapiconsumerclient.request;

import com.wutolas.nbpapiconsumerclient.validator.DateMaxConstraint;
import com.wutolas.nbpapiconsumerclient.validator.DateDaysLimitConstraint;
import com.wutolas.nbpapiconsumerclient.validator.order.ExchangeRatesCheck;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ExchangeRatesRequest {

    @NotNull(message = "{exchangeRatesRequest.dateFrom.NotNull}")
    @DateMaxConstraint(
            dateMax = "now",
            message = "{exchangeRatesRequest.dateFrom.DateMax}",
            groups = ExchangeRatesCheck.class)
    @DateDaysLimitConstraint(
            daysLimit = 367,
            dateMax = "now",
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

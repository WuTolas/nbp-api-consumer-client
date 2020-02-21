package com.wutolas.nbpapiconsumerclient.request;

import com.wutolas.nbpapiconsumerclient.validator.ApiNbpDateBoundariesConstraint;
import com.wutolas.nbpapiconsumerclient.validator.ApiNbpSingleCurrencyDaysLimitConstraint;
import com.wutolas.nbpapiconsumerclient.validator.order.ExchangeRatesCheck;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ExchangeRatesRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{exchangeRatesRequest.dateFrom.NotNull}")
    @ApiNbpDateBoundariesConstraint(
            message = "{exchangeRatesRequest.dateFrom.ApiNbpDateBoundariesConstraint}",
            groups = ExchangeRatesCheck.class)
    @ApiNbpSingleCurrencyDaysLimitConstraint(
            message = "{exchangeRatesRequest.dateFrom.ApiNbpSingleCurrencyDaysLimitConstraint}",
            groups = ExchangeRatesCheck.class)
    private LocalDate dateFrom;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }
}

package com.wutolas.nbpapiconsumerclient.request;

import com.wutolas.nbpapiconsumerclient.validator.ApiNbpDateBoundariesConstraint;
import com.wutolas.nbpapiconsumerclient.validator.order.ExchangeRatesCheck;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ExchangeRatesRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @ApiNbpDateBoundariesConstraint(
            message = "{exchangeRatesRequest.dateFrom.dateBoundariesConstraint}",
            groups = ExchangeRatesCheck.class)
    private LocalDate dateFrom;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }
}

package com.wutolas.nbpapiconsumerclient.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    private LocalDate effectiveDate;
    private Double bid;
    private Double ask;

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }
}

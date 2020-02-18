package com.wutolas.nbpapiconsumerclient.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesSeries {

    private String currency;
    private String code;
    private List<Rate> rates = new ArrayList<>();

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rate> getRates() {
        return rates;
    }
}

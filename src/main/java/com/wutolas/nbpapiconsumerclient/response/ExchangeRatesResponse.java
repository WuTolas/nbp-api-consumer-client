package com.wutolas.nbpapiconsumerclient.response;

import java.util.LinkedList;
import java.util.List;

public class ExchangeRatesResponse {

    private String currency;
    private String code;
    private List<RateResponse> rates = new LinkedList<>();

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RateResponse> getRates() {
        return rates;
    }

    public void setRates(List<RateResponse> rates) {
        this.rates = rates;
    }
}

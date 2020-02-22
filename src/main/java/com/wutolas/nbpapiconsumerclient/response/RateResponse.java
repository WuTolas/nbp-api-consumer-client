package com.wutolas.nbpapiconsumerclient.response;

import java.time.LocalDate;

public class RateResponse {

    private LocalDate effectiveDate;
    private Double bid;
    private Double ask;
    private Double bidDifference;
    private Double askDifference;

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBidDifference() {
        return bidDifference;
    }

    public void setBidDifference(Double bidDifference) {
        this.bidDifference = bidDifference;
    }

    public Double getAskDifference() {
        return askDifference;
    }

    public void setAskDifference(Double askDifference) {
        this.askDifference = askDifference;
    }
}

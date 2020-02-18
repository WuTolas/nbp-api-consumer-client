package com.wutolas.nbpapiconsumerclient.response;

public class RateResponse {

    private String effectiveDate;
    private Double bid;
    private Double ask;
    private Double bidDifference;
    private Double askDifference;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
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

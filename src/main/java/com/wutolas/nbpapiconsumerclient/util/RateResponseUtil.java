package com.wutolas.nbpapiconsumerclient.util;

import com.wutolas.nbpapiconsumerclient.helper.MathHelper;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;

@Component
public class RateResponseUtil {

    private final MathHelper mathHelper;

    public RateResponseUtil(MathHelper mathHelper) {
        this.mathHelper = mathHelper;
    }

    public void calculateBidAskDifferences(List<RateResponse> rates) {
        double bidDifference;
        double askDifference;
        RateResponse previousRate;
        RateResponse currentRate;
        ListIterator<RateResponse> listIterator = rates.listIterator();

        if (listIterator.hasNext()) {
            previousRate = listIterator.next();

            while (listIterator.hasNext()) {
                currentRate = listIterator.next();

                bidDifference = mathHelper.subtractBigDecimal(currentRate.getBid(), previousRate.getBid(), 4);
                askDifference = mathHelper.subtractBigDecimal(currentRate.getAsk(), previousRate.getAsk(), 4);
                currentRate.setBidDifference(bidDifference);
                currentRate.setAskDifference(askDifference);

                previousRate = currentRate;
            }
        }

    }

}

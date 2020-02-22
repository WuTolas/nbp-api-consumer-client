package com.wutolas.nbpapiconsumerclient.converter;

import com.wutolas.nbpapiconsumerclient.model.api.Rate;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RateToRateResponse implements Converter<Rate, RateResponse> {

    @Override
    public RateResponse convert(@NonNull Rate source) {

        RateResponse rateResponse = new RateResponse();
        rateResponse.setEffectiveDate(source.getEffectiveDate());
        rateResponse.setAsk(source.getAsk());
        rateResponse.setBid(source.getBid());

        return rateResponse;
    }
}

package com.wutolas.nbpapiconsumerclient.converter;

import com.wutolas.nbpapiconsumerclient.model.api.Rate;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RateToRateResponse implements Converter<Rate, RateResponse> {

    private final static Logger log = LogManager.getLogger(RateToRateResponse.class);

    @Override
    public RateResponse convert(@NonNull Rate source) {

        RateResponse rateResponse = new RateResponse();
        rateResponse.setEffectiveDate(source.getEffectiveDate());
        rateResponse.setAsk(source.getAsk());
        rateResponse.setBid(source.getBid());

        log.debug(
                "Successfully converted {} to {}",
                () -> source.getClass().getSimpleName(),
                () -> rateResponse.getClass().getSimpleName()
        );

        return rateResponse;
    }
}

package com.wutolas.nbpapiconsumerclient.util;

import com.wutolas.nbpapiconsumerclient.helper.MathHelper;
import com.wutolas.nbpapiconsumerclient.response.RateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

class RateResponseUtilTest {

    @Mock
    private MathHelper mathHelper;
    @InjectMocks
    private RateResponseUtil rateResponseUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateBidAskDifferencesShouldGenerateDifferenceValuesForSecondElementInList() {
        //given
        RateResponse rateResponse1 = new RateResponse();
        RateResponse rateResponse2 = new RateResponse();

        rateResponse1.setBid(5.4545);
        rateResponse1.setAsk(5.2233);
        rateResponse2.setBid(5.2123);
        rateResponse2.setAsk(5.2011);

        double bidDiff = -0.2422;
        double askDiff = -0.0222;

        List<RateResponse> rates = new LinkedList<>();
        rates.add(rateResponse1);
        rates.add(rateResponse2);

        given(mathHelper.subtractBigDecimal(anyDouble(), anyDouble(), anyInt()))
                .willReturn(bidDiff)
                .willReturn(askDiff);

        //when
        rateResponseUtil.calculateBidAskDifferences(rates);

        //then
        assertThat(rateResponse2.getBidDifference(), is(bidDiff));
        assertThat(rateResponse2.getAskDifference(), is(askDiff));
    }

    @Test
    void calculateBidAskDifferencesShouldntGenerateAnythingForFirstElementInList() {
        //given
        RateResponse rateResponse1 = new RateResponse();
        RateResponse rateResponse2 = new RateResponse();

        rateResponse1.setBid(5.4545);
        rateResponse1.setAsk(5.2233);
        rateResponse2.setBid(5.2123);
        rateResponse2.setAsk(5.2011);

        double bidDiff = -0.2422;
        double askDiff = -0.0222;

        List<RateResponse> rates = new LinkedList<>();
        rates.add(rateResponse1);
        rates.add(rateResponse2);

        given(mathHelper.subtractBigDecimal(anyDouble(), anyDouble(), anyInt()))
                .willReturn(bidDiff)
                .willReturn(askDiff);

        //when
        rateResponseUtil.calculateBidAskDifferences(rates);

        //then
        assertNull(rateResponse1.getAskDifference());
        assertNull(rateResponse1.getBidDifference());
    }
}
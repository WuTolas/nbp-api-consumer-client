package com.wutolas.nbpapiconsumerclient.helper;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MathHelperTest {

    private final MathHelper mathHelper = new MathHelper();

    @Test
    void subtractBigDecimalWith4PointsPrecisionShouldReturnExpectedValue() {
        //given
        double a = 5.12344;
        double b = 5.0;
        int precision = 4;
        double expected = 0.1234;

        //when
        double result = mathHelper.subtractBigDecimal(a, b, precision);

        //then
        assertThat(result, is(expected));
    }
}
package com.wutolas.nbpapiconsumerclient.helper;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class MathHelper {

    public double subtractBigDecimal(double x, double y, int precision) {
        BigDecimal a = new BigDecimal(x);
        BigDecimal b = new BigDecimal(y);
        MathContext mathContext = new MathContext(precision);

        return a.subtract(b, mathContext).doubleValue();
    }

}

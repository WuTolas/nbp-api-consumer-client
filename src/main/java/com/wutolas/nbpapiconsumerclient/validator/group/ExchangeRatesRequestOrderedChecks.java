package com.wutolas.nbpapiconsumerclient.validator.group;

import com.wutolas.nbpapiconsumerclient.validator.order.ExchangeRatesCheck;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ Default.class, ExchangeRatesCheck.class })
public interface ExchangeRatesRequestOrderedChecks {
}

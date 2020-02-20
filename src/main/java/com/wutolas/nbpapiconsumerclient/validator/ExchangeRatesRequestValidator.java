package com.wutolas.nbpapiconsumerclient.validator;

import com.wutolas.nbpapiconsumerclient.request.ExchangeRatesRequest;
import com.wutolas.nbpapiconsumerclient.validator.element.DateBoundariesValidator;
import com.wutolas.nbpapiconsumerclient.validator.element.DateFormatValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class ExchangeRatesRequestValidator implements Validator {

    private final DateFormatValidator dateFormatValidator;
    private final DateBoundariesValidator dateBoundariesValidator;

    public ExchangeRatesRequestValidator(
            DateFormatValidator dateFormatValidator,
            DateBoundariesValidator dateBoundariesValidator
    ) {
        this.dateFormatValidator = dateFormatValidator;
        this.dateBoundariesValidator = dateBoundariesValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ExchangeRatesRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ExchangeRatesRequest exchangeRatesRequest = (ExchangeRatesRequest) o;

        if (!dateFormatValidator.isValid(exchangeRatesRequest.getDateFrom())) {
            errors.rejectValue("dateFrom", "Data powinna być w następującym formacie: YYYY-MM-DD");
        } else if (!dateBoundariesValidator.isValid(exchangeRatesRequest.getDateFrom())) {
            errors.rejectValue("dateFrom", "Data wykracza dozwolony zakres");
        }

    }
}

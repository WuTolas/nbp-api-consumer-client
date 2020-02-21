package com.wutolas.nbpapiconsumerclient.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ApiNbpSingleCurrencyDaysLimitValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiNbpSingleCurrencyDaysLimitConstraint {
    String message() default "Max days limit reached";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


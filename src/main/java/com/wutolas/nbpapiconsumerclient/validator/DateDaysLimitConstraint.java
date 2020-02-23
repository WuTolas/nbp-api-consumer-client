package com.wutolas.nbpapiconsumerclient.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DateDaysLimitValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDaysLimitConstraint {
    String message() default "Max days limit reached";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int daysLimit();
    String dateMax();
    String datePattern() default "yyyy-MM-dd";
}


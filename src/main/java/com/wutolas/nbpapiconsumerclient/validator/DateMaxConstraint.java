package com.wutolas.nbpapiconsumerclient.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DateMaxValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateMaxConstraint {
    String message() default "Incorrect date range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String dateMax();
    String datePattern() default "yyyy-MM-dd";
}

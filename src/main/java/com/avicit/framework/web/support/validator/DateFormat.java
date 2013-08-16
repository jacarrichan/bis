package com.avicit.framework.web.support.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface DateFormat {
    String message() default "date.format.error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value() default "(19|20)\\d{2}-(0?\\d|1[012])-(0?\\d|[12]\\d|3[01])";
}

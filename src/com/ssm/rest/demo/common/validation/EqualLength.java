package com.ssm.rest.demo.common.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ssm.rest.demo.common.validation.validator.EqualLengthValidator;

/**
 * 等于长度注解
 *
 * @author zhujunjie
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualLengthValidator.class)
public @interface EqualLength {

    String message() default "equal_length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}


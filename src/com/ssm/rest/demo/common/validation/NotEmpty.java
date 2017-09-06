package com.ssm.rest.demo.common.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ssm.rest.demo.common.validation.validator.NotEmptyValidator;

/**
 * 非空注解
 *
 * @author zhujunjie
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {

    String message() default "not_empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


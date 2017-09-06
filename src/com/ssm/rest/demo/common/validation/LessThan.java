package com.ssm.rest.demo.common.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ssm.rest.demo.common.validation.validator.LessThanValidator;

/**
 * 小于值注解
 *
 * @author huangyong
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LessThanValidator.class)
public @interface LessThan {

    String message() default "less_than";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}

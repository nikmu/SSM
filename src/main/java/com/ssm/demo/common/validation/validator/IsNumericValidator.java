package com.ssm.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.demo.common.utils.StringUtil;
import com.ssm.demo.common.validation.IsNumeric;

/**
 * 数字注解验证器
 *
 * @author zhujunjie
 * @since 1.0.0
 */
public class IsNumericValidator implements ConstraintValidator<IsNumeric, String> {

    @Override
    public void initialize(IsNumeric constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtil.isNumeric(value);
    }
}


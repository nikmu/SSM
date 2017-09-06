package com.ssm.rest.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.rest.demo.common.utils.StringUtil;
import com.ssm.rest.demo.common.validation.NotEmpty;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

	@Override
	public void initialize(NotEmpty constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtil.isNotEmpty(value);
	}
}

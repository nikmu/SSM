package com.ssm.rest.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.rest.demo.common.validation.MinLength;

public class MinLengthValidator implements ConstraintValidator<MinLength, String> {

	private int length;
	
	@Override
	public void initialize(MinLength constraintAnnotation) {
		length = constraintAnnotation.value();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() >= length;
	}
}

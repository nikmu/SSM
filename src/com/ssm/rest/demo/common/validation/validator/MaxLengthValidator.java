package com.ssm.rest.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.rest.demo.common.validation.MaxLength;

public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

	private int length;
	
	@Override
	public void initialize(MaxLength constraintAnnotation) {
		length = constraintAnnotation.value();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() <= length;
	}
}

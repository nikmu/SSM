package com.ssm.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.demo.common.validation.GreaterThan;

public class GreaterThanValidator implements ConstraintValidator<GreaterThan, Integer> {

	private Integer value;
	
	@Override
	public void initialize(GreaterThan constraintAnnotation) {
		value = constraintAnnotation.value();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value > this.value;
	}
}

package com.ssm.rest.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.rest.demo.common.validation.LessThan;

public class LessThanValidator implements ConstraintValidator<LessThan, Integer>{

	private Integer value;
	
	@Override
	public void initialize(LessThan constraintAnnotation) {
		value = constraintAnnotation.value();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value < this.value;
	}
}

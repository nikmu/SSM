package com.ssm.demo.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ssm.demo.common.validation.EqualLength;

/**
 * 等于长度注解验证器
 *
 * @author zhujunjie
 *
 */
public class EqualLengthValidator implements ConstraintValidator<EqualLength, String> {

	private int length;
	
	@Override
	public void initialize(EqualLength constraintAnnotation) {
		length = constraintAnnotation.value();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() == length;
	}

}

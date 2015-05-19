package com.endava.aminternship.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
	@Autowired
	private UserService userService;

	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
		
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(userService == null){
			return true;
		}			
		return userService.findUserByEmail(value) == null;
	}

}

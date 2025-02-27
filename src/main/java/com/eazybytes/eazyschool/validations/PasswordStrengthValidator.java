package com.eazybytes.eazyschool.validations;

import com.eazybytes.eazyschool.annotation.PasswordValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements
        ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator passwordValidator) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid(String passwordField,
                           ConstraintValidatorContext cxt) {  // In this mehod we have not used ConstraintValidatorContext cxt
        return passwordField != null && (!weakPasswords.contains(passwordField));
        // you could use the ConstraintValidatorContext object to:
        //
        //Get the custom error message defined in the @PasswordValidator annotation and use it to create a custom constraint violation
        //Get the current validation context to validate the password in relation to other properties of the object being validated

    }
}
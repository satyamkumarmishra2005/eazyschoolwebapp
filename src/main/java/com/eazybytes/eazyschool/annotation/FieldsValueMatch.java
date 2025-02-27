package com.eazybytes.eazyschool.annotation;

import com.eazybytes.eazyschool.validations.FieldsValueMatchValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class) // In Spring Boot, you can create a custom validator using the @Constraint annotation.
// It is used to define custom validation logic that can be applied to fields in DTOs or entity classes.

// validatedBy = FieldsValueMatchValidator.class  // The buisness logic of the custom annotation is defined in the FieldsValueMatchValidator class.

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
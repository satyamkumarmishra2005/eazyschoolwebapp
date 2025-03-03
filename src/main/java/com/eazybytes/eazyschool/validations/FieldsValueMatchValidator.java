package com.eazybytes.eazyschool.validations;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator
        implements ConstraintValidator<FieldsValueMatch, Object> {  // Here we have used Object because here we have to compare two filds
    // so we will pass entire class and then fetch the required fields feom that class

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
//        if (fieldValue != null) {
//            if (fieldValue.toString().startsWith("$2a")) { // Ignore the validation done byu spring data jpa whenever
//                // password is encrypted or starts with
//
//                return true;
//            } else {
//                return fieldValue.equals(fieldMatchValue);
//            }
//        } else {
//            return fieldMatchValue == null;
//        }

        if(fieldValue!= null){
            return fieldValue.equals(fieldMatchValue);
        }
        else {
            return fieldMatchValue==null;
        }

    }
}

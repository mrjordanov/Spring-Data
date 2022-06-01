package com.example.mvc.util;




import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class MyValidator {

    private final Validator localValidator;

    public MyValidator() {
        localValidator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public boolean isValid(Object object) {
        Set<ConstraintViolation<Object>> validate = localValidator.validate(object);

        return validate.isEmpty();
    }
}

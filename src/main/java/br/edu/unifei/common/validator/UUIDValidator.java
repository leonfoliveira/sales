package br.edu.unifei.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<IsUUID, String> {
    @Override
    public void initialize(IsUUID isUUID) {
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Invalid UUID param: " + uuid).addConstraintViolation();

        String regex = "[\\da-f]{8}-[\\da-f]{4}-[1-5][\\da-f]{3}-[89ab][\\da-f]{3}-[\\da-f]{12}";
        return uuid.matches(regex);
    }
}
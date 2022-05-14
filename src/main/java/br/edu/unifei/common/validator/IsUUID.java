package br.edu.unifei.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UUIDValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUUID {
    String message() default "Invalid UUID param";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

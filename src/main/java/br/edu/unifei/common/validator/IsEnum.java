package br.edu.unifei.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Value cannot be null")
public @interface IsEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "Value must be a valid enum";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

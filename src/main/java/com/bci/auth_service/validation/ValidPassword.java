package com.bci.auth_service.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "Contraseña inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


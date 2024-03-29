package ado.rush.university.util;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = PasswordValidatorConstraint.class)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PasswordValid {
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

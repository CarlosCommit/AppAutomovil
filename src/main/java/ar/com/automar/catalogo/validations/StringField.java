package ar.com.automar.catalogo.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringFieldValidator.class)
public @interface StringField {
    String message() default "El campo debe ser una cadena.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
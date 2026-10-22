package com.proyecto.peliculas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DirectorMayorDeEdadValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectorMayorDeEdad {

    String message() default "El director debe tener al menos {edadMinima} años en el año de estreno";

    int edadMinima() default 18;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

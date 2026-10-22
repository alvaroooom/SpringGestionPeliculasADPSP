package com.proyecto.peliculas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Validación a nivel de clase
 * Verifica que el director de una película tenga al menos una edad mínima.*/
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

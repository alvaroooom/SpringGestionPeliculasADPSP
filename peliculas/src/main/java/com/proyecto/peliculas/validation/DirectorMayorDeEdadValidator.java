package com.proyecto.peliculas.validation;

import com.proyecto.peliculas.dto.PeliculaRequestDTO;
import com.proyecto.peliculas.repo.DirectorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DirectorMayorDeEdadValidator
        implements ConstraintValidator<DirectorMayorDeEdad, PeliculaRequestDTO> {

    private final DirectorRepository directorRepository;

    private int edadMinima;

    @Override
    public void initialize(DirectorMayorDeEdad constraintAnnotation) {
        this.edadMinima = constraintAnnotation.edadMinima();
    }

    @Override
    public boolean isValid(PeliculaRequestDTO dto, ConstraintValidatorContext context) {
        // Los campos nulos ya los controlan @NotNull; un director inexistente lo
        // resuelve el servicio con un 404.
        if (dto == null || dto.directorId() == null || dto.fechaEstreno() == null) {
            return true;
        }

        boolean valida = directorRepository.findById(dto.directorId())
                .map(director -> director.getAnioNacimiento() == null
                        || dto.fechaEstreno().getYear() - director.getAnioNacimiento() >= edadMinima)
                .orElse(true);

        if (!valida) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("fechaEstreno")
                    .addConstraintViolation();
        }

        return valida;
    }
}

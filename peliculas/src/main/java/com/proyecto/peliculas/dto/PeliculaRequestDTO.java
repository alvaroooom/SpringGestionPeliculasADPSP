package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.validation.DirectorMayorDeEdad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@DirectorMayorDeEdad
public record PeliculaRequestDTO(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no puede superar los 150 caracteres")
        String titulo,

        @NotBlank(message = "El género es obligatorio")
        @Size(max = 50, message = "El género no puede superar los 50 caracteres")
        String genero,

        @NotNull(message = "La fecha de estreno es obligatoria")
        LocalDate fechaEstreno,

        @NotNull(message = "El id del director es obligatorio")
        @Positive(message = "El id del director debe ser positivo")
        Long directorId
) {
}

package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DirectorRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @NotNull(message = "El año de nacimiento es obligatorio")
        @Min(value = 1800, message = "El año de nacimiento no puede ser anterior a 1800")
        Integer anioNacimiento
) {

    public Director toEntity() {
        return Director.builder()
                .nombre(nombre)
                .anioNacimiento(anioNacimiento)
                .build();
    }
}

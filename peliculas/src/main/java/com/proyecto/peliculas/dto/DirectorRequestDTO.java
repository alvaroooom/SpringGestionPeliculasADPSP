package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(
        name = "DirectorRequestDTO",
        description = "Datos necesarios para crear o actualizar un director"
)
public record DirectorRequestDTO(
        @Schema(
                description = "Nombre del director",
                example = "Christopher Nolan"
        )
        @NotBlank(message = "{directorRequestDTO.nombre.notBlank}")
        @Size(max = 100, message = "{directorRequestDTO.nombre.size}")
        String nombre,

        @Schema(
                description = "Año de nacimiento del director",
                example = "1970"
        )
        @NotNull(message = "{directorRequestDTO.anioNacimiento.notNull}")
        @Min(value = 1800, message = "{directorRequestDTO.anioNacimiento.min}")
        Integer anioNacimiento
) {

    public Director to() {
        return Director.builder()
                .nombre(nombre)
                .anioNacimiento(anioNacimiento)
                .build();
    }
}

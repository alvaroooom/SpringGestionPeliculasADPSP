package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DirectorResponseDTO",
        description = "Detalle de un director"
)
public record DirectorResponseDTO(
        @Schema(
                description = "Identificador único del director",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nombre del director",
                example = "Christopher Nolan"
        )
        String nombre,

        @Schema(
                description = "Año de nacimiento del director",
                example = "1970"
        )
        Integer anioNacimiento
) {

    public static DirectorResponseDTO of(Director director) {
        return new DirectorResponseDTO(
                director.getId(),
                director.getNombre(),
                director.getAnioNacimiento()
        );
    }
}

package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DirectorSimpleDTO",
        description = "Versión reducida de un director, para incluir dentro de una película"
)
public record DirectorSimpleDTO(
        @Schema(
                description = "Identificador único del director",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nombre del director",
                example = "Christopher Nolan"
        )
        String nombre
) {

    public static DirectorSimpleDTO of(Director director) {
        return new DirectorSimpleDTO(director.getId(), director.getNombre());
    }
}

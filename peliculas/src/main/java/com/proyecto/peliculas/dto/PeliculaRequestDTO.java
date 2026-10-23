package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.validation.DirectorMayorDeEdad;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(
        name = "PeliculaRequestDTO",
        description = "Datos necesarios para crear o actualizar una película"
)
@DirectorMayorDeEdad(message = "{peliculaRequestDTO.directorMayorDeEdad}")
public record PeliculaRequestDTO(
        @Schema(
                description = "Título de la película (único)",
                example = "Origen"
        )
        @NotBlank(message = "{peliculaRequestDTO.titulo.notBlank}")
        @Size(max = 150, message = "{peliculaRequestDTO.titulo.size}")
        String titulo,

        @Schema(
                description = "Género de la película",
                example = "Ciencia ficción"
        )
        @NotBlank(message = "{peliculaRequestDTO.genero.notBlank}")
        @Size(max = 50, message = "{peliculaRequestDTO.genero.size}")
        String genero,

        @Schema(
                description = "Fecha de estreno de la película",
                example = "2010-07-16"
        )
        @NotNull(message = "{peliculaRequestDTO.fechaEstreno.notNull}")
        LocalDate fechaEstreno,

        @Schema(
                description = "Id de un director existente",
                example = "1"
        )
        @NotNull(message = "{peliculaRequestDTO.directorId.notNull}")
        @Positive(message = "{peliculaRequestDTO.directorId.positive}")
        Long directorId
) {
}

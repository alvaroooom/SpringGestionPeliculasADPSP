package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Pelicula;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(
        name = "PeliculaResponseDTO",
        description = "Detalle de una película, con su director y su reparto"
)
public record PeliculaResponseDTO(
        @Schema(
                description = "Identificador único de la película",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Título de la película",
                example = "Origen"
        )
        String titulo,

        @Schema(
                description = "Género de la película",
                example = "Ciencia ficción"
        )
        String genero,

        @Schema(
                description = "Fecha de estreno de la película",
                example = "2010-07-16"
        )
        LocalDate fechaEstreno,

        @Schema(description = "Director de la película")
        DirectorSimpleDTO director,

        @Schema(description = "Actores que forman parte del reparto, con su rol")
        List<ActorEnRepartoDTO> reparto
) {

    public static PeliculaResponseDTO of(Pelicula pelicula) {
        return new PeliculaResponseDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno(),
                DirectorSimpleDTO.of(pelicula.getDirector()),
                pelicula.getReparto().stream()
                        .map(ActorEnRepartoDTO::of)
                        .toList()
        );
    }
}

package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Pelicula;

import java.time.LocalDate;
import java.util.List;

public record PeliculaResponseDTO(
        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        DirectorSimpleDTO director,
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

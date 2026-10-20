package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Pelicula;

import java.time.LocalDate;

public record PeliculaResponseDTO(
        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        DirectorSimpleDTO director
) {

    public static PeliculaResponseDTO of(Pelicula pelicula) {
        return new PeliculaResponseDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno(),
                DirectorSimpleDTO.of(pelicula.getDirector())
        );
    }
}

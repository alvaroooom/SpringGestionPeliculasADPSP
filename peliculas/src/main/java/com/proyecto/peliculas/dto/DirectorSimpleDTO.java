package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;

public record DirectorSimpleDTO(
        Long id,
        String nombre
) {

    public static DirectorSimpleDTO of(Director director) {
        return new DirectorSimpleDTO(director.getId(), director.getNombre());
    }
}

package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;

public record DirectorResponseDTO(
        Long id,
        String nombre,
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

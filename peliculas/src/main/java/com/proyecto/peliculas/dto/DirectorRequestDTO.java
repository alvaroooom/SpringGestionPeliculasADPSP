package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Director;

public record DirectorRequestDTO(
        String nombre,
        Integer anioNacimiento
) {

    public Director toEntity() {
        return Director.builder()
                .nombre(nombre)
                .anioNacimiento(anioNacimiento)
                .build();
    }
}

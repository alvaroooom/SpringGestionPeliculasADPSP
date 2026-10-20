package com.proyecto.peliculas.dto;

import java.time.LocalDate;

public record PeliculaRequestDTO(
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        Long directorId
) {
}

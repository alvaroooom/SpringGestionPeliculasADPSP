package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Actor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActorRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre
) {

    public Actor toEntity() {
        return Actor.builder()
                .nombre(nombre)
                .build();
    }
}

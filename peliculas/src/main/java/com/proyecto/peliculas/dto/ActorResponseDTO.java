package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Actor;

public record ActorResponseDTO(
        Long id,
        String nombre
) {

    public static ActorResponseDTO of(Actor actor) {
        return new ActorResponseDTO(actor.getId(), actor.getNombre());
    }
}

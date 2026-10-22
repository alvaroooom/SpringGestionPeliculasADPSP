package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Actor;

public record ActorSimpleDTO(
        Long id,
        String nombre
) {

    public static ActorSimpleDTO of(Actor actor) {
        return new ActorSimpleDTO(actor.getId(), actor.getNombre());
    }
}

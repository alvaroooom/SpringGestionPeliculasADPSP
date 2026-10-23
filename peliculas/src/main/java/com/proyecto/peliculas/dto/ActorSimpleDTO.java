package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Actor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ActorSimpleDTO",
        description = "Versión reducida de un actor, para incluir dentro del reparto de una película"
)
public record ActorSimpleDTO(
        @Schema(
                description = "Identificador único del actor",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Nombre del actor",
                example = "Cillian Murphy"
        )
        String nombre
) {

    public static ActorSimpleDTO of(Actor actor) {
        return new ActorSimpleDTO(actor.getId(), actor.getNombre());
    }
}

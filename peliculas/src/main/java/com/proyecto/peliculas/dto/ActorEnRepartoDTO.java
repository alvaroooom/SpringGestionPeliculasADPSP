package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Reparto;
import com.proyecto.peliculas.model.RolActor;

public record ActorEnRepartoDTO(
        ActorSimpleDTO actor,
        RolActor rol
) {

    public static ActorEnRepartoDTO of(Reparto reparto) {
        return new ActorEnRepartoDTO(ActorSimpleDTO.of(reparto.getActor()), reparto.getRol());
    }
}

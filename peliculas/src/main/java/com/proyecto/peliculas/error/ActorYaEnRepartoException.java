package com.proyecto.peliculas.error;

public class ActorYaEnRepartoException extends RuntimeException {

    public ActorYaEnRepartoException(Long peliculaId, Long actorId) {
        super("El actor con id %d ya forma parte del reparto de la película con id %d"
                .formatted(actorId, peliculaId));
    }
}

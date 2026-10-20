package com.proyecto.peliculas.error;

public class PeliculaYaExisteException extends RuntimeException {

    public PeliculaYaExisteException(String titulo) {
        super("Ya existe una película con el título '%s'".formatted(titulo));
    }
}

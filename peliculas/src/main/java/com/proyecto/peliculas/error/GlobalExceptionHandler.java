package com.proyecto.peliculas.error;

import com.proyecto.peliculas.error.notfound.EntidadNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ProblemDetail handleNoEncontrada(EntidadNoEncontradaException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Recurso no encontrado");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://example.com/errors/not-found"));

        return problem;
    }

    @ExceptionHandler(PeliculaYaExisteException.class)
    public ProblemDetail handlePeliculaYaExiste(PeliculaYaExisteException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("Película ya existente");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://example.com/errors/pelicula-ya-existe"));

        return problem;
    }
}

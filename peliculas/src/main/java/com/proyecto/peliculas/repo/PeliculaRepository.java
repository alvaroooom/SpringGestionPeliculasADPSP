package com.proyecto.peliculas.repo;

import com.proyecto.peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    boolean existsByTituloIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCaseAndIdNot(String titulo, Long id);
}

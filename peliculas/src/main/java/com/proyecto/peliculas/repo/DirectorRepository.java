package com.proyecto.peliculas.repo;

import com.proyecto.peliculas.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}

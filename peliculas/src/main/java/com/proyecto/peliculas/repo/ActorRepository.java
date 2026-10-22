package com.proyecto.peliculas.repo;

import com.proyecto.peliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}

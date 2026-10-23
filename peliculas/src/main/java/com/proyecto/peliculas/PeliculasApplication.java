package com.proyecto.peliculas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info =
	@Info(description = "Una API REST para gestionar directores, películas y el reparto de actores",
			version = "1.0",
			contact = @Contact(email = "munoz.fealv23@triana.salesianos.edu"),
			license = @License(name = "CC BY"),
			title = "API de gestión de películas"
	)
)
@SpringBootApplication
public class PeliculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeliculasApplication.class, args);
	}

}

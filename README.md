# Gestión de películas

API REST hecha con Spring Boot para el proyecto de Acceso a Datos y Programación de Servicios y Procesos. Sirve para gestionar directores, películas y los actores que salen en cada una, con el rol que hacen (protagonista, villano, etc.).

## Cómo arrancarlo

Hace falta tener el JDK 25. Maven no, porque el proyecto lleva el wrapper.

```bash
cd peliculas
./mvnw spring-boot:run
```

En Windows es `mvnw.cmd spring-boot:run`. Arranca en http://localhost:8080 y carga unos cuantos datos de ejemplo (4 directores, 5 películas, 6 actores). La base de datos es H2 y se vacía cada vez que se reinicia la aplicación.

## Modelo de datos

- **Director**: id, nombre, año de nacimiento.
- **Película**: id, título (único), género, fecha de estreno y su director.
- **Actor**: id y nombre.

Un director tiene muchas películas. Entre películas y actores hay una relación muchos a muchos, y para poder guardar el rol de cada actor en cada película la he hecho con una tabla intermedia (`Reparto`). Los roles son `PROTAGONISTA`, `SECUNDARIO`, `VILLANO`, `HEROE` y `CAMEO`.

## Endpoints

Todos empiezan por `/api/v1`.

| Recurso | Qué se puede hacer |
|---|---|
| `/directores` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| `/peliculas` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| `/actores` | `GET`, `POST` |
| `/peliculas/{peliculaId}/actores/{actorId}` | `POST` para meter un actor en el reparto de una película |

Al crear una película hay que pasar el `directorId` de un director que ya exista. El `GET /peliculas/{id}` devuelve la película con su director y su reparto.

Para asignar un actor se puede mandar el rol en el cuerpo, y si no se manda se pone `SECUNDARIO`:

```json
{ "rol": "VILLANO" }
```

## Validaciones y errores

Los datos de entrada se validan (campos obligatorios, longitudes, año de nacimiento mínimo...). Además hay una validación propia, `@DirectorMayorDeEdad`: el director tiene que tener al menos 18 años en el año en que se estrena la película.

Los errores salen en formato `ProblemDetail`:

- **400**: datos no válidos.
- **404**: no existe el director, la película o el actor.
- **409**: ya existe una película con ese título, el actor ya estaba en el reparto de esa película, o se intenta borrar un director que todavía tiene películas.

## Swagger y Postman

Con la aplicación en marcha, la documentación está en http://localhost:8080/swagger-ui.html.

En la carpeta `postman` está la colección con peticiones para probar todo. Se importa en Postman y ya está. Usa los ids de los datos de ejemplo, así que si la quieres ejecutar entera conviene reiniciar antes la aplicación.

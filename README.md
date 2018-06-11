# Arquitectura de un mini API-Rest simulado
> Este proyecto pretende ser un ejemplo sencillo de arquitectura de un API-Rest simulado para comprender las capas que intervienen y la organización de los diferentes tipos de test, con integración continua y control de la calidad del código
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Arquitectura y Patrones para Aplicaciones Web*

## Tecnologías necesarias
* Java
* Maven
* GitHub

## Diseño de entidades
![themes-entities-class-diagram](https://github.com/miw-upm/APAW-themes-layers/blob/master/docs/themes-entities-class-diagram.png)

## API
### POST /users
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
- `email`: String
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### PUT /users/{id}
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
- `email`: String
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### POST /suggestions
### POST /themes
### GET /themes
### DELETE /themes/{id}
### PATH/themes/{id}/category
### POST /themes/{id}/votes
### GET /themes/{id}/average

##### Autor: Jesús Bernal Bermúdez U.P.M.

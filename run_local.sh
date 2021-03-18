#!/bin/bash
export DATABASE_URL= # url con el siguiente formato jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME} donde:
## DB_HOST: host de la base de datos.
## DB_PORT: puerto de la base de datos.
## DB_NAME: nombre de la base de datos
export DATABASE_USERNAME= ##Usuario de la base de datos
export DATABASE_PASSWORD= ##Contraseña del usuario

./mvnw spring-boot:run

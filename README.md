# Desafío Técnico Mercado Libre

## Visión general

El desafío consiste en resolver un problema de *triangulación*, específicamente el caso de trilateración.

### Requerimientos

La solución fue desarrollada usando lo siguiente:

* Java 1.8
* Spring Boot 2.4.3
* Maven
* Docker
* Heroku
* PostgreSQL

Y empleando una estructura de capas que se detallan a continuación:

* configuration : contiene toda la configuración general del proyecto.
* controller : contiene todas las firmas de los servicios implementados.
* dto : contiene todas las clases usadas para los Request y Response.
* enums : contiene todas las constantes usadas.
* exception: contiene el manejo custom de las excepciones.
* model : contiene las entidades que se crean en base de datos.
* repository : contiene las interfaces de conexión a la base de datos.
* service: contiene toda la lógica de negocio.

## Deploy

La solución se encuentra desplegada en Heroku

URL del swagger : https://ml-challenge-jp.herokuapp.com/api/ml-challege/swagger-ui/index.html

## Ejecución en localhost

Para todos los casos se asume que la aplicación está corriendo en el puerto 8080

url del Swagger: http://localhost:8080/api/ml-challege/swagger-ui/index.html

### Usando un IDE

Para Intellij, realizar las siguientes configuraciones:

1. Ir a ejecutar -> Editar configuraciones
2. Haga clic en (+) Agregar configuración -> Aplicación
3. Establecer un nombre para la aplicación
4. Establecer el Main_class: com.ml.challenge.Application
5. Establecer las variables de entorno
6. Guarde los cambios y ejecute

### Usando el Sh (Linux)

Es necesario tener instalado Java y Sh.

1. Editar el archivo *run_local.sh*
2. Añadir los valores de las variables de entorno con los valores necesarios
3. Ejecutar el script con `sh run_local.sh`

### Usando Docker

Es necesario tener instalado docker

1. Generar la imagen del proyecto `docker build -t ml-challenge .`
2. Ajustar los valores de las variables del archivo ml_challenge.env
3. Ejecutar la imagen del
   docker `docker run -d -it -p 8080:8080 --env-file=ml_challenge.env --name=ml-challenge ml-challenge`

## Environments

### Required Environment Variables

```
DATABASE_URL: url con el siguiente formato jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
DATABASE_USERNAME: Usuario de la base de datos
DATABASE_PASSWORD: Contraseña del usuario

```

## Resumen general del desafío y su solución

El desafío constan en general constan de dos partes determinar la posición de un objeto (Nave) y descifrar el mensaje
que este emite

### Determinar la posición de una nave

Este parte del desafío consta en resolver un problema de trilateración, el cual consiste en determinar la posición de
una nave a partir de tres satélites de los cuales conocemos sus coordenadas (x, y) y su distancia a la nave.

La solución más general de este problema se encuentra en un espacio 3D (y, y, z), y su solución consiste en encontrar,
el punto común donde 3 esferas (las esferas se generan tomando la distancia del punto al objeto como el radio de la
misma) se interceptan, para ello se genera el siguiente sistema de ecuaciones no lineales:

```
 r1^2 = (x - x1)^2 + (y - y1)^2  + (z - z1)^2
 r2^2 = (x - x2)^2 + (y - y2)^2  + (z - z2)^2
 r3^2 = (x - x3)^2 + (y - y3)^2  + (z - z3)^2
```

Y para el caso particular del problema no tenemos el eje Z y el sistema nos queda de la siguiente manera:

```
 r1^2 = (x - x1)^2 + (y - y1)^2 
 r2^2 = (x - x2)^2 + (y - y2)^2 
 r3^2 = (x - x3)^2 + (y - y3)^2 
```

El cual luego de resolver usando el método de igualación y sustitución, encontramos el valor para x y y, que viene hacer
la posición de la nave.

### Descifrar el mensaje que se emite

Para descifrar el mensaje, primero se busca la entrada con mayor longitud porqué en el peor de los casos el mensaje esa
sería el tamaño del mensaje.

Se usó la siguiente estrategia:

Se creó una lista auxiliar para ir añadiendo las palabras no vacías que se van encontrando en las listas a procesar, si
esa posición de la lista está vacía se añade la palabra, en caso contrario sé válida si la palabra no se encuentra en la
lista, y en caso afirmativo se reemplaza.

## Contribuidor

- José Prado

## Licencia

None
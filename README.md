# APIRest

Aplicación creada para la consulta de iformación del API [OMDBApi](https://www.omdbapi.com/).Todos los protocolos de comunicación se basarán en Http y los formatos de mensajería de intercambio serán en base JSON además que el cliente invocará los servicios Rest por medio de JavaScript.

El API Utilizada para este ejercicio cuenta con una cantidad significativa de peliculas con su respectiva información general, como título, año de lanzamiento, Director, lenguajes, actores, etc...

## Disponibilidad de la aplicación

Para hacer disposición la aplicación primero es necesario descargar el contenido de este repositorio, que ya cuenta con las llaves de acceso a la información del API, utilizando el siguiente comando en la terminal.

```
git clone https://github.com/JohanSGarciaM/API-Rest-Service
```
Debemos compilar el código fuente con el siguiente comando

```
mvn clean install
```
Para hacer uso de la aplicación utilizamos el comando:

```
mvn exec:java
```

A partir de este momento el socket esta activo para recibir la información al puerto 35000 por lo que nos dirijimos desde nuestro browser al puerto.

```
localhost:35000
```

Una vez enviada la solicitud a la API externa, y dado el formato html para recibir la información, vamos a recibir una página que funciona como buscador para poder definir cual será la pelicula que deseamos investigar.

![](/apirest/resources/buscador.jpg)

En el campo de texto debemos introducir el título de la pelicual que deseamos buscar y luego dar click en el botón "search" para así hacer la solicitud al API de la información de la película correspondiente

![](/apirest/resources/shrek.jpg)






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

Para poder revisar la documentación del proyecto es necesario ejecutar el siguiente comando en la terminar para que se ejecute en el navegador.

```
mvn javadoc:javadoc
```

Una vez enviada la solicitud a la API externa, y dado el formato html para recibir la información, vamos a recibir una página que funciona como buscador para poder definir cual será la pelicula que deseamos investigar.

![](/resources/buscador.jpg)

En el campo de texto debemos introducir el título de la pelicual que deseamos buscar y luego dar click en el botón "search" para así hacer la solicitud al API de la información de la película correspondiente.

![](/resources/shrek.jpg)


##Arquitectura

La arquitectura de este proyecto se basa en la estipulada por el profesor en el enunciado del taller en moodle, donde se cuenta en primera instancia con un servidor que servirá, como esta mencionado, de gateway para encapsular llamadas a otros servidores web externos, este servidor fachada se encuentra separado por capas, lo que permite ser más entendible y escalable, distribuido en las carpetas , controller, persistence y service.

Las cuatro partes implicadas en el proyecto están definidas de la siguiente manera:

- **Js Web Client:** Esta parte es encargada de tener contacto directo con el usuario y recibir la información de entrada que este ingrese para así poder hacer el request al API
- **Web Server with Rest API Facade:** Esta parte se encarga de procesar las funciones que se pueden realizar por parte del usuario al API, de igual manera tendra una estructura de almacenamiento de datos que funciona como un caché para guardar las peliculas que ya se han consultado con aterioridad.
- **External Rest API:** Esta API es la estipulada en el enunciado del taller con la que vamos a trabajar, para tener acceso a esta fue necesario solicitar una llave y así poder tener acceso a la información del catálogo de peliculas que esta brinda.
- **Concurrent Java Test Client:** Esta es la parte del proyecto que se encarga de realizar pruebas a la funcionalidad del código.

![](/resources/architecture.png)







## <p align="center"> FORO HUB</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/3769e5a6-c42e-4227-b211-89afdd132f64" alt="forohub Logo" width="300">
</p>
API Rest desarrollada en Java con Spring Boot para la gestión de un foro, el proyecto permite manejar tópicos y autenticar usuarios.


## Índice

1. [Funcionalidades](#Funcionalidades)
2. [Requerimientos previos](#requerimientos-previos)
3. [Configuración](#configuración)
4. [Tecnologías utilizadas](#tecnologías-utilizadas)
5. [Estructura del proyecto](#estructura-del-proyecto)


## Funcionalidades

- [x] Autenticación de usuarios con JWT y Spring Security
  - `POST /login` : Inicia sesión y obtiene un Token JWT.

- [x] Registro de un nuevo tópico
  - Endpoints
    * `POST /topics` : Crea un nuevo tópico
  - Reglas de negocio
    * Todos los campos son obligatorios, por lo tanto, es necesario verificar si todos los campos se están ingresando correctamente.
    * La API no debe permitir el registro de tópicos duplicados (con el mismo título y mensaje).

- [x] Mostrar todos los tópicos
  - Endpoints
    * `GET /topics?sort=creationDate` : Muestra todos los tópicos
    * `GET /topics/searchByCourseOrYear?year={year}&nameCourse={nameCourse}&sort=creationDate`: Buscar por curso o año
  - Reglas de negocio
    * Mostrar los primeros 10 resultados ordenados por fecha de creación del tópico en orden ASC
    * Mostrar los resultados usando un criterio de búsqueda: nombre del curso o un año determinado
    * Obtener la respuesta con paginación para controlar el volumen de los datos

- [x] Leer el tópico por id
  - Endpoints
    * `GET /topics/{id}` : Obtiene un tópico por id
  - Reglas de negocio
    * Los datos del tópico a devolver: id, titulo, mensaje, fecha de creación, estado, autor y nombre del curso
    * En caso de no encontrar el tópico retornará un 404

- [x] Actualizar un tópico
  - Endpoints
    * `PUT /topics/{id}` : Edita un tópico por id
  - Reglas de negocio
    * Los datos del tópico a devolver: id, titulo, mensaje, fecha de creación, estado, autor y nombre del curso
    * En caso de no encontrar el tópico retornará un 404

- [x] Eliminar un tópico
  - Endpoints
    * `DELETE /topics/{id}` : Borra un tópico por id
  - Reglas de negocio
    * En caso de no encontrar el tópico retornará un 404
    * Se realizará una eliminación lógica a través de la propiedad status: 1 (activo) y 0 (inactivo)

## Requerimientos previos

- **JDK: Java 17 o superior**
- **Gestor de dependencias: Maven 3.9.9**
- **Spring Boot 3.4**
- **Base de datos MySQL**

## Configuración 

  1. Clona el repositorio
     
     ```bash
     git clone https://github.com/Luiggi-piero/forohub-springboot.git
     cd forohub-springboot
  2. Configura las variables de entorno para la conexión a la base de datos

     ```yaml
      spring.datasource.url=${DATASOURCE_URL}
      spring.datasource.username=${DATASOURCE_USERNAME}
      spring.datasource.password=${DATASOURCE_PASSWORD}
      spring.jpa.hibernate.ddl-auto=update
      api.security.secret=${JWT_SECRET}

  3. Crea un base de datos vacía con el nombre definido en DATASOURCE_URL
  
  4. Ejecuta el proyecto

  5. La aplicación estará disponible en: http://localhost:8080


## Tecnologías utilizadas

- **Spring Boot**: Desarrollo rápido y robusto de aplicaciones.
- **Spring Security y JWT**: Autenticación segura.
- **Flyway**: Migración y gestión de bases de datos.
- **MySQL**: Sistema de gestión de bases de datos relacional.          


## Estructura del proyecto

Arquitectura multicapas

      src
      ├── main
      │   ├── java/com/kronos/forohub
      │   │   ├── controller   -> REST controllers.
      │   │   ├── dto          -> Data transfer object layer.
      │   │   ├── model        -> Domain feature layer.
      │   │   ├── repository   -> Data persistence layer.
      │   │   ├── service      -> Business logic layer.
      │   │   ├── infra        -> Error handlers, security and docs.
      │   │   ├── validations  -> Custom validations.
      │   └── resources
      │       ├── application.properties -> Configuration app.
      │       └── db.migration.sql       -> Scripts SQL and migration with flyway.
      └── test
          └── java/com/kronos/forohub    -> Units Test and Integration. 


</br>
<p align="center">
  <img src="https://img.shields.io/badge/java-white?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=black">
  <img src="https://img.shields.io/badge/SPRINGBOOT-white?style=for-the-badge&logo=spring&logoColor=white&labelColor=%236DB33F">
  <img src="https://img.shields.io/badge/mysql-white?style=for-the-badge&logo=mysql&logoColor=white&labelColor=4169E1">
</p>


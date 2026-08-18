# Crear la base de datos en PostgresSQL
`CREATE DATABASE db_test`

Verificar que la base de datos esté corriendo por el puerto por defecto `5432`.
El usuario por defecto de la base es `postgres`
La contraseña es: `admin`

Importar el script **`BaseDatos.sql`**

Sí prefiere cambiar las configuraciones en el archivo `application.properties `

## Development server
Ejecutar `java -jar msc-service-0.0.1-SNAPSHOT.jar` por defecto `http://localhost:8080/mscServices`.

Los datos de inicio de sesión para oauth: `http://localhost:8080/mscServices/oauth/token`

Auth Type: **Basic Auth**
Username: **titania-web**
Password: **12345**

# Application
Usuario: **admin**
Contraseña: **12345**
#
Pruebas Postman
`Titania.postman_collection.json`
#

### Configuración usada por defecto

- Base de datos: `db_test`
- Usuario de PostgreSQL: `postgres`
- Contraseña de PostgreSQL: `admin`
- Puerto PostgreSQL: `5432`
- Puerto `msc-service`: `8080`

### Acceso a los servicios

Una vez levantado el entorno:

- PostgreSQL: `localhost:5432`
- OAuth token: `http://localhost:8080/mscServices/oauth/token`
- `msc-service`: `http://localhost:8080/mscServices`


# msc-root

Prueba Técnica -> Arquitectura Microservicio

El proyecto base de Spring Boot tiene una estructura en capas, basado en subproyectos, dicha estructura facilita la
gestión y la comprensión del código.

A continuación se muestra la descripción general de la estructura en capas para el proyecto:

## Capa client (msc-client)

Esta capa es un subproyecto y se encarga de alojar:
* Entidades
* Interfaces de servicios
* Interfaces de repositorios
* Excepciones personalizadas
* Utilitarios comunes
* Constantes

## Capa core (msc-core)

La capa core es prácticamente el corazón de todo el proyecto, aquí encontraremos las reglas de negocio y la lógica de
programación.
Aquí se alojan los siguientes apartados:

* Configuraciones
* Implementación de repositorios
* Implementación de servicios

## Capa dto. (msc-dto)

En este subproyecto básicamente encontramos lo que son las clases que representan objetos utilizados para transmitir
datos entre capas.

## Capa service (msc-service)

La capa services es un subproyecto que corresponde a los controladores, se encarga de exponer las API o las
funcionalidades del proyecto.
Básicamente, corresponde a la capa de controllers y es la primera capa donde inicia todo.
Un "endpoint" en la capa de controladores (controllers) se refiere a un punto de acceso específico dentro de una
aplicación que permite la comunicación entre el cliente y el servidor.
Los endpoints son gestionados por los controladores y están asociados a acciones u operaciones específicas que pueden
ser realizadas por la aplicación.

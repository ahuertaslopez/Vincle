# Demo Application

Esta es una aplicacion DEMO correspondiente al Ejercicio 2 expuesto en el archivo `VINCLE-Backend-Test_v1.0_20220505.pdf`.


## Getting Started

### Prerequisitos

- Java 17
- Maven
- SQLite

### Instalación

1. Descomprimir proyecto

2. Compilar proyecto:

    ```sh
    mvn clean install
    ```

3. Ejecutar aplicación:

    ```sh
    mvn spring-boot:run
    ```

4. La aplicación estará funcionando a través de `http://localhost:8080/items/`

### Configuración

La aplicacion usa una base de datos de SQLite. La base de datos se encuentra dentro de la carpeta ./data/. 
La configuración de la base de datos puede encontrarse en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:sqlite:./data/items.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

## Aclaraciones

1. Se aporta dentro del proyecto los siguientes ficheros:
 * Colección Postman con los métodos del CRUD desarrollado: `DEMO-Vincle.postman_collection.json`
 * Archivo DockerFile para dockerizar el proyecto
 * Archivo docker-compose.yml, para unificar en una misma imagen docker la parte back y la parte front desarrollada
 * Carpeta Frontend, con la interface gráfica para visualizar y analizar la colección de items. Por favor, acuda a frontend/README.md para más información.

2. Debido a problemas técnicos con la máquina en la que se ha desarrollado esta demo, no se ha podido probar la parte de dockerización.

3. Dentro de la parte test, se ha puesto una aplicación java (`com.vincle.ahl.demo.simulatorItemSimulator.java`) para la creación automática y aleatoria de items a partir del desarrollo realizado.

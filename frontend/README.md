# Frontend Application

Esta es la capa frontend del proyecto demo. Provee una interfaz gráfica para visualizar y analizar la colección de items que se gestiona a partir de la capa backend.

## Getting Started

### Prerequisitos

- Node.js
- npm (Node Package Manager)

### Instalación

1. Situese en el directorio `frontend`:

    ```sh
    cd frontend
    ```

2. Instale las dependencias:

    ```sh
    npm install
    ```

3. Inicie el servidor de desarrollo:

    ```sh
    npm start
    ```

4. El servidor se encuentra corriendo en `http://localhost:3000`

### Configuración

La aplicación frontend está configurada para interactuar con el API backend en `http://localhost:8080/items/`.

Si necesita cambiar la URL del API, actualice la configuración en el fichero correspondiente (`src/api.js`).

### Estructura del proyecto

- `src`: Código fuente de la aplicación frontend.
- `public`: Recursos públicos y archivo HTML (por defecto).

## Aclaraciones

1. Se aporta dentro del proyecto los siguientes ficheros:
 * Archivo DockerFile para dockerizar el proyecto front

2. Debido a problemas técnicos con la máquina en la que se ha desarrollado esta demo, no se ha podido probar la parte de dockerización.

3. No se ha conseguido implementar la actualizacón en Tiempo Real mediante eventos de WebSocket.

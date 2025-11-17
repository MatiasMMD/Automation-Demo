<h1 align="center">🤖 Framework de Automatización de Pruebas BDD 🤖</h1>

<p align="center">
  <a href="https://github.com/MatiasMMD/Automation-Demo/actions/workflows/main.yml"><img src="https://github.com/MatiasMMD/Automation-Demo/actions/workflows/main.yml/badge.svg?branch=main" alt="Build Status"></a>
  <img src="https://img.shields.io/badge/Java-21-blue?logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/Selenium-4-green?logo=selenium" alt="Selenium 4">
  <img src="https://img.shields.io/badge/Cucumber-7-green?logo=cucumber" alt="Cucumber 7">
  <img src="https://img.shields.io/badge/Gradle-8.5-blue?logo=gradle" alt="Gradle 8.5">
  <img src="https://img.shields.io/badge/JUnit-5-blue?logo=junit5" alt="JUnit 5">
</p>



Este repositorio contiene un framework de automatización de pruebas para aplicado a la página web [DemoBlaze](https://www.demoblaze.com/index.html), construido con Java y Selenium. El proyecto está diseñado para ser robusto, escalable y fácil de mantener, siguiendo las mejores prácticas de la industria.

**Objetivo Principal:** Este proyecto funciona como una suite de pruebas de regresión que valida las funcionalidades principales de la página web DemoBlaze (autenticación, navegación, gestión del carrito y proceso de compra). Adicionalmente, su propósito es demostrar la implementación de patrones de diseño clave como **Page Object Model (POM)** y la metodología **Behavior-Driven Development (BDD)** con Cucumber, sirviendo como una pieza de portafolio para exhibir mis habilidades técnicas en automatización de QA.

<p align="center">
  <br>
  <em>Demostración de la ejecución de una prueba E2E (Compra Exitosa)</em>
  <br>
  <img src="https://github.com/user-attachments/assets/6210c584-9520-4ea8-a738-92249deb9bc4" alt="Demostración de Pruebas Automatizadas" width="800"/>
</p>


## 📚 Tabla de Contenidos

- [Stack de Tecnologías](#-stack-de-tecnologías)
- [Características Principales (Patrones de Diseño)](#-características-principales-design-patterns)
- [Configuración y Requisitos Previos](#-configuración-y-requisitos-previos)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Cómo Ejecutar las Pruebas](#-cómo-ejecutar-las-pruebas)
- [Reportes](#-reportes)


## 🛠️ Stack de Tecnologías

Este proyecto utiliza un conjunto de herramientas y librerías modernas para el desarrollo y ejecución de pruebas automatizadas:

| Tecnología | Versión | Propósito |
| :--- | :--- | :--- |
| **Java** | 21 | Lenguaje de programación principal. |
| **Selenium WebDriver** | 4.36.0 | Automatización de interacciones con el navegador. |
| **Cucumber** | 7.28.0 | Implementación de BDD para escribir pruebas en Gherkin. |
| **JUnit** | 5.14.0 | Framework para la ejecución de pruebas. |
| **Gradle** | - | Herramienta de automatización de compilación y gestión de dependencias. |
| **WebDriverManager** | 5.9.2 | Gestión automática de los drivers de navegador (chromedriver, geckodriver, etc.). |
| **Log4j2** | 2.23.1 | Sistema de logging para registrar eventos durante la ejecución. |


## ✨ Características Principales (Design Patterns)

- **Behavior-Driven Development (BDD):** Utiliza Gherkin (`.feature` files) para escribir escenarios de prueba en un lenguaje natural y comprensible. Esto fomenta la colaboración entre desarrolladores, QAs y stakeholders no técnicos.

- **Page Object Model (POM):** Cada página de la aplicación web está representada por una clase Java. Esta clase contiene los localizadores de los elementos web (WebElements) y los métodos que encapsulan las interacciones del usuario con esa página. Esto mejora la mantenibilidad y reduce la duplicación de código.

- **Singleton Pattern:** Se utiliza para gestionar una única instancia del `WebDriver` a lo largo de toda la ejecución de las pruebas, asegurando que todas las pruebas se ejecuten en la misma sesión del navegador y optimizando el rendimiento.

- **Cucumber Hooks:** Se emplean los hooks `@Before` y `@After` para gestionar el ciclo de vida del `WebDriver`. El hook `@Before` se encarga de inicializar el navegador antes de cada escenario, mientras que el hook `@After` lo cierra al finalizar, garantizando un entorno de prueba limpio.


## ⚙️ Configuración y Requisitos Previos

Sigue estos pasos para configurar y ejecutar el proyecto en tu equipo local.

### Requisitos Previos

- **Java Development Kit (JDK):** Versión 21 o superior.
- **Gradle:** Instalado en tu sistema y disponible en el PATH, o puedes usar el Gradle Wrapper (`gradlew`) incluido en el proyecto.
- **IDE (Opcional):** IntelliJ IDEA o Eclipse con soporte para Gradle.

### Instalación

1.  **Clonar el repositorio:**
    ```sh
    git clone <https://github.com/MatiasMMD/Automation-Demo>
    cd <NOMBRE-DEL-DIRECTORIO>
    ```

2.  **Instalar dependencias:**
    El Gradle Wrapper se encargará de descargar la versión correcta de Gradle y todas las dependencias del proyecto.
    ```sh
    # Para Windows
    .\gradlew build

    # Para macOS/Linux
    ./gradlew build
    ```
    Este comando compilará el código y descargará todas las librerías necesarias definidas en `build.gradle`.



## 📂 Estructura del Proyecto

El proyecto sigue una estructura estándar de Maven/Gradle que separa claramente el código de la aplicación, las pruebas y los recursos.

```plaintext
/
├── .gradle/              # Archivos de caché y configuración de Gradle.
├── build/                # Directorio de salida para artefactos compilados y reportes.
├── gradle/               # Contiene el Gradle Wrapper.
├── logs/                 # Archivos de log generados por Log4j2.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/    # Implementación del Page Object Model. Cada clase representa una página y sus interacciones.
│   │   │   └── utils/    # Clases de utilidad (WebDriverSetup, ConfigReader, ActionsHelper).
│   │   └── resources/    # Archivos de configuración y propiedades del framework.
│   │       ├── config.properties                 # Centraliza la configuración del navegador(URL base, timeouts).
│   │       ├── credentials.properties.example    # Muestra un ejemplo de la estructura de las credenciales requeridas para los test.
│   │       └── log4j2.xml                        # Configuración de la dependencia de log4j2.
│   └── test/
│       ├── java/
│       │   ├── runner/   # Contiene la clase TestRunner para configurar y ejecutar las pruebas de Cucumber.
│       │   └── steps/    # Contiene las clases de Step Definitions ("glue code") y los Hooks (@Before, @After).
│       └── resources/
│           └── features/ # Archivos .feature con los escenarios de prueba escritos en Gherkin.
├── build.gradle          # Define las dependencias, plugins y tareas del proyecto.
├── gradlew               # Script de ejecución del Gradle Wrapper para macOS/Linux.
├── gradlew.bat           # Script de ejecución del Gradle Wrapper para Windows.
└── README.md             # Este archivo.
```

## 🚀 Cómo Ejecutar las Pruebas

Puedes ejecutar las pruebas automatizadas directamente desde la línea de comandos usando Gradle.

### Ejecutar todas las pruebas

Este comando buscará el `TestRunner` y ejecutará todos los escenarios de prueba definidos en los archivos `.feature`.

```sh
# Para Windows
.\gradlew test

# Para macOS/Linux
./gradlew test
```

### Ejecutar pruebas por Tags

Puedes filtrar los escenarios a ejecutar pasando una etiqueta (tag) de Cucumber. Esto es útil para ejecutar conjuntos específicos de pruebas (ej. `smoke`, `regression`, `login`).

El proyecto está configurado para aceptar propiedades del sistema y pasarlas a Cucumber.

```sh
# Ejemplo: Ejecutar solo los escenarios con la etiqueta @Smoke
# Para Windows
.\gradlew test -Dcucumber.filter.tags="@Smoke"

# Para macOS/Linux
./gradlew test -Dcucumber.filter.tags="@Smoke"
```


## 📊 Reportes

Después de cada ejecución, se generan reportes que proporcionan una visión detallada de los resultados.

1.  **Reporte de JUnit/Gradle:**
    Gradle genera automáticamente un reporte HTML. Puedes encontrarlo en la siguiente ruta después de ejecutar `.\gradlew test`:
    `build/reports/tests/test/index.html`

2.  **Logs de Ejecución:**
    Los logs detallados de la ejecución de las pruebas se guardan en el archivo `logs/app.log`, según la configuración de `log4j2.xml`.


## 🏗️ Arquitectura del Framework

Para una inmersión profunda en el "esqueleto" de este framework y una explicación detallada de la funcionalidad de cada componente principal (como `BasePage`, `WebDriverSetup`, `Hooks`, `TestRunner`, `build.gradle`, etc.), te invito a visitar el siguiente repositorio donde se documenta la plantilla base: 

**➡️ [Base Automation Framework v1.0 ](https://github.com/MatiasMMD/Base-Framework-v1.0)**

Este recurso complementario explica cómo cada pieza del rompecabezas se une para crear un entorno de automatización robusto y escalable.


## ✍️ Autor

- **LinkedIn:** [Matías Marino Dans](https://www.linkedin.com/in/mmarinodans/)
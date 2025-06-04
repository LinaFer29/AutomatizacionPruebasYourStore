# Proyecto de Automatización de Pruebas - OpenCart

## Descripción del Proyecto
Este proyecto consiste en la automatización de pruebas funcionales para la página de prueba [OpenCart](https://opencart.abstracta.us/). El objetivo principal es validar los flujos críticos del sistema, como el registro de usuarios, el inicio de sesión, la búsqueda y agregado de productos al carrito, y la validación de los productos en el carrito, utilizando herramientas y estrategias modernas de automatización.

El proyecto está diseñado para ser modular, escalable y fácil de mantener, siguiendo buenas prácticas como el patrón **Page Object Model (POM)** y un enfoque **Data-Driven Testing**.

---

## Integrantes del Proyecto
- **[Lina María Fernández García]**
- **[Joan Sebastian Tunubala Sánchez]**
- **[Gissel Vanessa Quitian]**
- **[Juan José Pizo Camacho]**
- **[Luis Edwar Mosquera Congo]**

---

## Funcionalidades Automatizadas
1. **Registro de Usuario**:
   - Validación del flujo de registro con datos dinámicos.
   - Lectura de datos de usuarios desde un archivo Excel.

2. **Inicio de Sesión**:
   - Lectura de combinaciones de email y contraseña previamente registrados desde el Excel.
   - Validación del flujo de inicio de sesión exitoso y con credenciales invalidas.
     
4.  **Búsqueda y Agregado de Productos al Carrito**:
   - Selección de categorías y subcategorías.
   - Agregado de múltiples productos al carrito desde un archivo Excel.

4. **Validación de Productos en el Carrito**:
   - Comparación de los productos agregados con los datos del archivo Excel.
   - Escritura de resultados en un archivo Excel para análisis posterior.

5. **Flujo End-to-End**:
   - Integración de todos los pasos anteriores en un flujo continuo que simula el recorrido completo del usuario.

---

## Herramientas y Tecnologías Utilizadas
- **Lenguaje de Programación**: Java
- **Framework de Pruebas**: TestNG
- **Automatización de Navegadores**: Selenium WebDriver
- **Manejo de Datos**: Apache POI (para leer y escribir archivos Excel)
- **Gestión de Dependencias**: Maven
- **IDE**: IntelliJ IDEA
- **Página de Pruebas**: [OpenCart](https://opencart.abstracta.us/)

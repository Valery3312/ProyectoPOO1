Claves y contraseñas de usuario 
id_usuario: 1722559166
Nombre usuario: Valery Balseca
Contraseña: 123
Rol: admin

# Proyecto CRUD de Competencia de Videojuegos

Este proyecto es una aplicación de escritorio desarrollada en Java con Swing que permite gestionar usuarios, jugadores, productos, clientes y facturas para una competencia de videojuegos.

---

## Características principales

- **Gestión de Usuarios:** Registro, edición, eliminación y listado de usuarios con roles (ADMIN y CAJERO).
- **Login Seguro:** Autenticación con validación de usuario y contraseña, redirigiendo según rol.
- **Roles y Permisos:** Diferenciación de funcionalidades y acceso entre Administradores y Cajeros.
- **CRUD Completo:** Operaciones de Crear, Leer, Actualizar y Eliminar para las entidades principales (usuarios, jugadores, productos, clientes, facturas).
- **Conexión a Base de Datos:** Uso de MySQL para almacenamiento persistente de datos.
- **Arquitectura MVC:** Organización del código en paquetes modelo (model), acceso a datos (dao), interfaz gráfica (ui) y utilidades (util).

---

## Tecnologías y herramientas

- Lenguaje: Java
- Interfaz gráfica: Swing
- Base de datos: MySQL
- IDE: IntelliJ IDEA
- Control de versiones: Git y GitHub

---

## Instrucciones para ejecutar

1. Clonar este repositorio.
2. Configurar la base de datos MySQL con las tablas y usuarios necesarios.
3. Modificar la clase `ConexionBD` con los datos de conexión de tu base de datos.
4. Ejecutar la clase `Main` para iniciar la aplicación.
5. Ingresar al sistema con un usuario válido. Usuarios con rol ADMIN acceden a un menú diferente que usuarios con rol CAJERO.

---

## Estado actual

- La aplicación está funcional con las operaciones CRUD principales.
- El login valida correctamente usuarios y dirige a interfaces según su rol.
- La interfaz gráfica está implementada con formularios independientes para cada entidad.

---

## Autor

Desarrollado por Valery Balseca


link de video: https://youtu.be/IYKEUFdrTwU


 iNFORME
Introducción
Este proyecto consiste en el desarrollo de una aplicación de escritorio en Java utilizando Swing para la interfaz gráfica, con una base de datos MySQL para la persistencia de la información. El objetivo principal es crear un sistema que permita la gestión integral de una competencia de videojuegos, contemplando las entidades principales involucradas: usuarios, jugadores, productos, clientes y facturas.
Funcionalidades desarrolladas
1.	Gestión de Usuarios:
Se implementó un módulo para administrar usuarios del sistema con roles diferenciados (ADMIN y CAJERO). Los usuarios pueden crearse, actualizarse, eliminarse y listarse. La autenticación valida la identidad y el rol, condicionando el acceso a diferentes menús y funcionalidades.
2.	Autenticación y Roles:
El sistema incluye un login que verifica las credenciales contra la base de datos. Dependiendo del rol del usuario, se despliega un menú específico:
o	ADMIN: Acceso completo a todas las funcionalidades y gestión del sistema.
o	CAJERO: Acceso limitado a ciertas operaciones como manejo de clientes, productos y facturación.
3.	CRUD para Entidades Principales:
Se desarrollaron clases modelo, DAO (Data Access Object) y formularios Swing para gestionar las siguientes entidades:
o	Jugadores
o	Productos
o	Clientes
o	Facturas
Cada entidad permite crear, listar, actualizar y eliminar registros de la base de datos.
4.	Arquitectura MVC:
El proyecto sigue el patrón Modelo-Vista-Controlador para separar las responsabilidades:
o	Modelo: Clases que representan las entidades del sistema.
o	DAO: Clases que gestionan el acceso a la base de datos.
o	Vista (UI): Formularios y ventanas Swing para interacción con el usuario.
o	Utilidades: Clases auxiliares como la gestión de conexión a la base de datos.
5.	Buenas Prácticas y Mejoras Técnicas:
o	Uso de enumeraciones (enum) para representar los roles, garantizando mayor seguridad y claridad en el código.
o	Validaciones en las interfaces para evitar entradas incorrectas.
o	Manejo de excepciones para mejorar la robustez del sistema.
Estado actual
El proyecto se encuentra en una fase avanzada de desarrollo, con la mayoría de funcionalidades principales implementadas y la arquitectura estructurada. Sin embargo, el proyecto no se ha terminado debido a limitaciones de tiempo.
Quedan pendientes aspectos importantes como:
•	Mejoras en la experiencia de usuario (interfaces más amigables y completas).
•	Implementación de reportes y estadísticas.
•	Refactorización y optimización del código para mejor mantenimiento.
•	Pruebas exhaustivas de integración y manejo de errores.
•	Documentación completa y empaquetado para distribución.
Consideraciones finales
Este proyecto representa una base sólida para un sistema de gestión completo en un entorno de competencia de videojuegos. La modularidad y separación de capas facilitan futuras ampliaciones o adaptaciones. Se recomienda continuar el desarrollo para cubrir los puntos pendientes y realizar pruebas que aseguren su estabilidad y funcionalidad.



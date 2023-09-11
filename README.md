# Sistema de Inventario Automotriz

Este proyecto tiene como objetivo desarrollar un sistema de inventario para el sector automotriz para la empresa Nexos Software. El sistema permitirá un control efectivo de la mercancía, incluyendo registros, ediciones y eliminaciones.

## Funcionalidades Principales

### Registro de Mercancía
- El sistema permite registrar nueva mercancía con los siguientes datos:
  - Nombre del producto.
  - Cantidad.
  - Fecha de ingreso.
  - Usuario que realiza el registro.

- Restricciones:
  - No puede haber más de una mercancía con el mismo nombre.
  - La cantidad debe ser un número entero.
  - La fecha de ingreso debe ser menor o igual a la fecha actual.

### Edición de Mercancía
- La edición de mercancía sigue las mismas condiciones que el registro de nueva mercancía.
- Se registra el usuario que realiza la modificación y la fecha de la misma.

### Eliminación de Mercancía
- Solo el usuario que registró la mercancía puede eliminarla.

### Visualización de Mercancía
- El sistema permite mostrar la mercancía registrada en pantalla.
- Los filtros de búsqueda pueden ser por fecha, usuario y/o nombre del producto.
- Se debe utilizar al menos un filtro de búsqueda.

## Gestión de Usuarios
- El sistema registra a los usuarios con la siguiente información:
  - Nombre.
  - Edad.
  - Cargo.
  - Fecha de ingreso a la compañía.

- Cargos predefinidos: Asesor de ventas, Administrador y Soporte.
- Posibilidad de agregar nuevos cargos en el futuro.

## Notas Adicionales
- No es necesario implementar un sistema de inicio de sesión para determinar qué usuario realiza las acciones. Se selecciona el usuario de un menú desplegable al realizar una acción.

Este sistema de inventario automotriz proporcionará a Nexos Software un control completo sobre su mercancía, mejorando la gestión y eficiencia en el sector automotriz.

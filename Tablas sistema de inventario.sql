-- Script para crear la tabla de tipo_cargo
CREATE TABLE tipo_cargo (
    ID INT PRIMARY KEY,
    nombre_cargo VARCHAR(255) NOT NULL
);

-- Script para crear la tabla de usuarios
CREATE TABLE usuarios (
    ID INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INT,
    tipo_cargo INT,
    fecha_ingreso DATE,
	FOREIGN KEY (tipo_cargo) REFERENCES tipo_cargo(ID)
);

-- Script para crear la tabla de producto
CREATE TABLE producto (
    ID INT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    cantidad INT,
    fecha_ingreso DATE CHECK (fecha_ingreso <= CURRENT_DATE),
    usuario_registra INT,
    usuario_modifica INT,
    fecha_modifica DATE,
    FOREIGN KEY (usuario_registra) REFERENCES usuarios(ID),
    FOREIGN KEY (usuario_modifica) REFERENCES usuarios(ID)
);

ALTER TABLE producto
ADD CONSTRAINT nombre_producto_Unico UNIQUE (nombre_producto);
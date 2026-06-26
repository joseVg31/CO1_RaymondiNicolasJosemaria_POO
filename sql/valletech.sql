-- Base de datos ValleTech
CREATE DATABASE IF NOT EXISTS valletech;
USE valletech;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- Usuario de prueba (contraseña: admin123)
INSERT INTO usuarios (usuario, contrasena) VALUES ('admin', 'admin123');

-- Productos de prueba
INSERT INTO productos (nombre, categoria, precio, stock) VALUES
('Laptop HP', 'Electrónica', 2500.00, 10),
('Mouse Logitech', 'Periféricos', 45.50, 50),
('Teclado Mecánico', 'Periféricos', 120.00, 30),
('Monitor 24"', 'Electrónica', 650.00, 15),
('Auriculares Sony', 'Audio', 89.99, 25);

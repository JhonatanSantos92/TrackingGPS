CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_baja TIMESTAMP
);

CREATE TABLE users_backup (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_baja TIMESTAMP
);

INSERT INTO users (nombre, correo, status) VALUES
('admin', 'admin@gps.com', 'ACTIVE'),
('juan', 'juan@gps.com', 'ACTIVE'),
('maria', 'maria@gps.com', 'ACTIVE'),
('pedro', 'pedro@gps.com', 'INACTIVE'),
('ana', 'ana@gps.com', 'ACTIVE');
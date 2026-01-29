CREATE TABLE dispositivo (
    id SERIAL PRIMARY KEY,
    imei VARCHAR(20) NOT NULL UNIQUE,
    modelo VARCHAR(50),
    estado VARCHAR(20) DEFAULT 'ACTIVE',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_baja TIMESTAMP
);

CREATE TABLE vehiculo (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(15) NOT NULL UNIQUE,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    annio INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_baja TIMESTAMP
);

CREATE TABLE asignacion (
    id SERIAL PRIMARY KEY,
    device_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    user_id BIGINT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_assignment_device
        FOREIGN KEY (device_id) REFERENCES dispositivo(id),

    CONSTRAINT fk_assignment_vehicle
        FOREIGN KEY (vehicle_id) REFERENCES vehiculo(id)
);

CREATE TABLE monitoreo (
    id BIGSERIAL PRIMARY KEY,
    assignment_id INT NOT NULL,
    latitud DECIMAL(9,6) NOT NULL,
    longitud DECIMAL(9,6) NOT NULL,
    velocidad DECIMAL(5,2),
    fecha TIMESTAMP NOT NULL,

    CONSTRAINT fk_monitoring_assignment
        FOREIGN KEY (assignment_id) REFERENCES asignacion(id)
);

CREATE TABLE alerta (
    id BIGSERIAL PRIMARY KEY,
    device_id BIGINT,
    vehicle_id BIGINT,
    type VARCHAR(255),
    message TEXT,
    created_at TIMESTAMP
);

INSERT INTO dispositivo (imei, modelo, estado) VALUES
('359881234567890', 'Queclink GL300', 'ACTIVE'),
('359881234567891', 'Queclink GL300', 'ACTIVE'),
('359881234567892', 'Teltonika FMB920', 'ACTIVE'),
('359881234567893', 'Teltonika FMB920', 'INACTIVE'),
('359881234567894', 'Cobán TK303', 'ACTIVE');

INSERT INTO vehiculo (placa, marca, modelo, annio) VALUES
('ABC-123', 'Toyota', 'Corolla', 2018),
('BCD-234', 'Hyundai', 'Elantra', 2019),
('CDE-345', 'Kia', 'Rio', 2020),
('DEF-456', 'Nissan', 'Sentra', 2017),
('EFG-567', 'Chevrolet', 'Sail', 2021);

INSERT INTO asignacion (device_id, vehicle_id, user_id) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 2),
(4, 4, 3),
(5, 5, 2);

INSERT INTO monitoreo (assignment_id, latitud, longitud, velocidad, fecha) VALUES
(1, -12.046374, -77.042793, 45.50, '2026-01-01 10:00:00'),
(1, -12.045800, -77.041500, 50.00, '2026-01-01 10:05:00'),
(2, -12.056374, -77.032793, 60.20, '2026-01-01 10:10:00'),
(3, -12.066374, -77.022793, 30.00, '2026-01-01 10:15:00'),
(4, -12.076374, -77.012793, 0.00,  '2026-01-01 10:20:00');
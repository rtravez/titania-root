INSERT INTO persons (creation_date, creation_host, creation_user, status, address, age, gender, identification, lastname, "name", telephone) VALUES(NOW(), '127.0.0.1', 'admin', true, 'La Concepción', 36, 'M', '1717172631', 'Administrador', 'Administrador', '0987350473');

INSERT INTO customers (creation_date, creation_host, creation_user, status, "password", username, person_id) VALUES (NOW(), '127.0.0.1', 'admin', true, '$2a$10$itMT/hPoLNo/FqNuzh69LeNMMUsca/j2WIdOn7P0ZYhjs6rKK/way', 'admin', 1);

INSERT INTO roles (creation_date, creation_host, creation_user, name, status) VALUES (NOW(), '127.0.0.1', 'admin', 'ROLE_ADMIN', true);

INSERT INTO role_customers (creation_date, creation_host, creation_user, customer_id, rol_id, status) VALUES (NOW(), '127.0.0.1', 'admin', 1, 1, true);

INSERT INTO vehiculos (creation_date, creation_host, creation_user, status, imei, placa, chasis, dealer, agencia, marca, modelo, plataforma) VALUES (NOW(), '127.0.0.1', 'admin', true, '864209041234567', 'PBA-1234', 'CHS98765432101', 'Dealer Quito', 'Agencia Cumbayá', 'Chevrolet', 'Grand Vitara', 'TrackGps');
INSERT INTO vehiculos (creation_date, creation_host, creation_user, status, imei, placa, chasis, dealer, agencia, marca, modelo, plataforma) VALUES (NOW(), '127.0.0.1', 'admin', true, '864209047654321', 'PBB-5678', 'CHS98765432102', 'Dealer Guayaquil', 'Agencia Samborondón', 'Toyota', 'Hilux', 'FleetNav');

INSERT INTO registros (creation_date, creation_host, creation_user, status, imei, fecha, latitud, longitud, velocidad, odometro, ignicion) VALUES (NOW(), '127.0.0.1', 'admin', true, '864209041234567', NOW(), -0.180653, -78.467838, 45.50, 12500.75, true);
INSERT INTO registros (creation_date, creation_host, creation_user, status, imei, fecha, latitud, longitud, velocidad, odometro, ignicion) VALUES (NOW(), '127.0.0.1', 'admin', true, '864209047654321', NOW(), -2.170998, -79.922359, 60.00, 34200.50, true);

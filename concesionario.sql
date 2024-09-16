CREATE DATABASE concesionario;
USE concesionario;

CREATE TABLE autos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category ENUM('vehiculo') DEFAULT 'vehiculo',
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO autos (name, image, description, price) VALUES
('Toyota Corolla', 'https://www.toyota.com.ec/admin/sites/default/files/2022-07/corolla%20sedan-potencia.png', 'Compacto eficiente con gran rendimiento de combustible.', 22000.00),
('Honda Civic', 'https://acnews.blob.core.windows.net/imgnews/paragraph/NPAZ_7b4309f6a957409ba2aa8d4d45881331.jpg', 'Auto compacto con un diseño moderno y características avanzadas.', 24000.00),
('Ford Mustang', 'https://www.vdm.ford.com/content/dam/vdm_ford/live/en_us/ford/nameplate/mustang/2025/collections/3-2/25_frd_mst_gt_crgr_ps34_356x180.png/jcr:content/renditions/cq5dam.web.1280.1280.png', 'Deportivo clásico con motor potente y diseño icónico.', 35000.00),
('Chevrolet Malibu', 'https://es.chevrolet.com/content/dam/chevrolet/na/us/english/vdc-collections/2025/cars/malibu/trims/2025-malibu-1zc69-1ls-gxd-trimselector.jpg?imwidth=960', 'Sedán espacioso con tecnología avanzada y comodidad.', 28000.00),
('Volkswagen Jetta', 'https://hips.hearstapps.com/hmg-prod/images/2025-volkswagen-jetta-107-6679be9c4f143.jpg?crop=0.780xw:0.659xh;0.111xw,0.255xh&resize=2048:*', 'Compacto con interiores de alta calidad y rendimiento sólido.', 23000.00);

Select * from autos;

CREATE TABLE motos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category ENUM('motocicleta') DEFAULT 'motocicleta',
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO motos (name, image, description, price) VALUES
('Yamaha YZF-R3', 'link', 'Deportiva ligera ideal para principiantes y amantes de la velocidad.', 5500.00),
('Kawasaki Ninja ZX-6R', 'link a la imagen', 'Motocicleta deportiva con un motor potente y tecnología avanzada.', 12000.00),
('Honda CB500F', 'link a la imagen', 'Motocicleta naked con buen equilibrio entre rendimiento y comodidad.', 6500.00),
('Suzuki GSX-R1000', 'link a la imagen', 'Motocicleta deportiva de alta cilindrada para entusiastas de la velocidad.', 15000.00),
('Ducati Monster 821', 'link a la imagen', 'Naked bike con diseño agresivo y rendimiento excepcional.', 13000.00);

Select * from motos;

CREATE TABLE pesados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category ENUM('pesado') DEFAULT 'pesado',
    price DECIMAL(10, 2) NOT NULL
);

Select * from pesados;
INSERT INTO pesados (name, image, description, price) VALUES
('Volvo FH16', 'link a la imagen', 'Camión de gran tonelaje con capacidad de carga impresionante y tecnología avanzada.', 95000.00),
('Kenworth W990', 'link a la imagen', 'Camión robusto con alto rendimiento y confort en carretera.', 85000.00),
('Scania R 500', 'link a la imagen', 'Camión de transporte con motor potente y características de lujo.', 90000.00),
('Mercedes-Benz Actros', 'link a la imagen', 'Camión con excelente eficiencia de combustible y confort.', 88000.00),
('Peterbilt 389', 'link a la imagen', 'Camión clásico con un diseño icónico y capacidad de carga robusta.', 91000.00);




CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  direccion VARCHAR(255),
  telefono VARCHAR(20)
);

INSERT INTO clientes (nombre, email, password, direccion, telefono) VALUES
('Carlos Mendoza', 'carlos.mendoza@example.com', 'password_hash_1', 'Calle Central 123', '0991234567'),
('Laura Gomez', 'laura.gomez@example.com', 'password_hash_2', 'Avenida Libertador 456', '0992345678'),
('Eduardo Ruiz', 'eduardo.ruiz@example.com', 'password_hash_3', 'Boulevard de los Sueños 789', '0993456789'),
('Sofia Torres', 'sofia.torres@example.com', 'password_hash_4', 'Calle de la Esperanza 321', '0994567890'),
('Jorge Morales', 'jorge.morales@example.com', 'password_hash_5', 'Avenida del Progreso 654', '0995678901');


select * from clientes;

CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    categoria ENUM('vehiculo', 'motocicleta', 'pesado') NOT NULL,
    item_id INT NOT NULL,
    cantidad INT NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CHECK (categoria IN ('vehiculo', 'motocicleta', 'pesado'))
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    fecha DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE SET NULL
);

CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    categoria ENUM('vehiculo', 'motocicleta', 'pesado') NOT NULL,
    item_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas(id) ON DELETE CASCADE,
    -- Implementar lógica de validación de categoría y item_id en la aplicación.
    CHECK (categoria = 'vehiculo' OR categoria = 'motocicleta' OR categoria = 'pesado')
);

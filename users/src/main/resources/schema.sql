-- Crear la tabla document_type si no existe
CREATE TABLE IF NOT EXISTS document_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);



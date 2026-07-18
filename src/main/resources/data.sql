-- Eliminar tablas si existen (para reiniciar)
DROP TABLE IF EXISTS expense;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS users;

-- Crear tabla users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) UNIQUE,   -- Agregamos nickname
    password VARCHAR(255) NOT NULL
);

-- Crear tabla category
CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Crear tabla expense
CREATE TABLE IF NOT EXISTS expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    date DATE,
    description VARCHAR(255),
    category_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insertar usuario por defecto (incluyendo nickname)
INSERT INTO users (name, nickname, password) VALUES ('Admin', 'admin', 'admin');
INSERT INTO users (name, nickname, password) VALUES ('Demo', 'demo', 'demo');

-- Insertar categorías por defecto
INSERT INTO category (name) VALUES ('Food');
INSERT INTO category (name) VALUES ('Transport');
INSERT INTO category (name) VALUES ('Entertainment');
INSERT INTO category (name) VALUES ('Bills');
INSERT INTO category (name) VALUES ('Shopping');
INSERT INTO category (name) VALUES ('Healthcare');
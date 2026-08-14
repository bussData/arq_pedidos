-- ============================================
-- Migration: V5__INSERT_SECURITY_DATA.sql
-- Description: Insertar roles y asignar credenciales a usuarios
-- Database: userdb
-- ============================================
-- Passwords codificados con BCrypt (strength 10):
--   admin123 → $2a$10$e77InV9/.OZ68nmbd9Co2uhuYu9g7eBNqu3nDyRHcC5x0cIH0YBJW
--   user123  → $2a$10$tQWrbvoAohyaYiDC6e9rNO9Wf7w0eLQamxD2TJhCWKXbJjqjRTXUu
-- ============================================

-- ============================================
-- 1. Insertar roles
-- ============================================
INSERT INTO roles (name, description) VALUES
('ROLE_ADMIN', 'Administrador del sistema - acceso total'),
('ROLE_RESTAURANT_MANAGER',  'Usuario restaurante - acceso total a catalogo'),
('ROLE_CLIENT',  'Usuario cliente - Consultas a catalogo y registro de pedidos'),
('ROLE_DRIVER',  'Persona encargada del delivery');

-- ============================================
-- 2. Actualizar passwords de usuarios existentes
-- ============================================
-- Juan Pérez → admin (password: admin123)
UPDATE users
SET password = '$2a$10$e77InV9/.OZ68nmbd9Co2uhuYu9g7eBNqu3nDyRHcC5x0cIH0YBJW',
    enabled = true
WHERE id = 1;

-- María García → user (password: user123)
UPDATE users
SET password = '$2a$10$tQWrbvoAohyaYiDC6e9rNO9Wf7w0eLQamxD2TJhCWKXbJjqjRTXUu',
    enabled = true
WHERE id = 2;

-- Carlos López → user (password: user123)
UPDATE users
SET password = '$2a$10$tQWrbvoAohyaYiDC6e9rNO9Wf7w0eLQamxD2TJhCWKXbJjqjRTXUu',
    enabled = true
WHERE id = 3;

-- Ana Torres → admin (password: admin123)
UPDATE users
SET password = '$2a$10$e77InV9/.OZ68nmbd9Co2uhuYu9g7eBNqu3nDyRHcC5x0cIH0YBJW',
    enabled = true
WHERE id = 4;

-- Roberto Sánchez → user (password: user123)
UPDATE users
SET password = '$2a$10$tQWrbvoAohyaYiDC6e9rNO9Wf7w0eLQamxD2TJhCWKXbJjqjRTXUu',
    enabled = true
WHERE id = 5;


--Julio Paredes y Luis Ruiz
-- Drivers (password: user123)
UPDATE users
SET password = '$2a$10$tQWrbvoAohyaYiDC6e9rNO9Wf7w0eLQamxD2TJhCWKXbJjqjRTXUu',
    enabled = true
WHERE id in (6,7);

-- ============================================
-- 3. Hacer password NOT NULL después de poblar
-- ============================================
ALTER TABLE users ALTER COLUMN password SET NOT NULL;

-- ============================================
-- 4. Asignar roles a usuarios
-- ============================================
-- Juan Pérez (id=1) → ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES
(1, (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

-- María García (id=2) → USER
INSERT INTO user_roles (user_id, role_id) VALUES
(2, (SELECT id FROM roles WHERE name = 'ROLE_USER'));

-- Carlos López (id=3) → ROLE_RESTAURANT_MANAGER
INSERT INTO user_roles (user_id, role_id) VALUES
(3, (SELECT id FROM roles WHERE name = 'ROLE_RESTAURANT_MANAGER'));

-- Ana Torres (id=4) → ROLE_RESTAURANT_MANAGER
INSERT INTO user_roles (user_id, role_id) VALUES
(4, (SELECT id FROM roles WHERE name = 'ROLE_RESTAURANT_MANAGER'));

-- Roberto Sánchez (id=5) → USER
INSERT INTO user_roles (user_id, role_id) VALUES
(5, (SELECT id FROM roles WHERE name = 'ROLE_USER'));

--Julio Paredes -> DRIVER
INSERT INTO user_roles (user_id, role_id) VALUES
    (6, (SELECT id FROM roles WHERE name = 'ROLE_DRIVER'));


--Luis Ruiz -> DRIVER
-- INSERT INTO user_roles (user_id, role_id) VALUES
    (7, (SELECT id FROM roles WHERE name = 'ROLE_DRIVER'));
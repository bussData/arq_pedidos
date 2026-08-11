    -- ============================================
    -- Migration: V1__CREATE_TABLES.sql
    -- Database: ordertdb (Docker container: postgres-order)
    -- ============================================

    -- Función para auto-actualizar updated_at
    CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
    BEGIN
        NEW.updated_at = CURRENT_TIMESTAMP;
        RETURN NEW;
    END;
    $$ language 'plpgsql';


    -- Tabla de order
    CREATE TABLE IF NOT EXISTS orders (
        id BIGSERIAL PRIMARY KEY,
        order_number VARCHAR(50) NOT NULL UNIQUE,
        user_id BIGINT NOT NULL,
        status VARCHAR(20) NOT NULL,
        total_amount NUMERIC(10,2) NOT NULL DEFAULT 0.00,
        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        );


    -- Trigger para actualizar updated_at
    CREATE TRIGGER update_orderes_updated_at
        BEFORE UPDATE ON orders
        FOR EACH ROW
        EXECUTE FUNCTION update_updated_at_column();

    -- Comentarios
    COMMENT ON TABLE Orders IS 'Ordenes del sistema - DB en Docker';

    CREATE TABLE IF NOT EXISTS order_items (
        id BIGSERIAL PRIMARY KEY,
        order_id BIGINT NOT NULL,
        product_id BIGINT NOT NULL,
        quantity INTEGER NOT NULL CHECK (quantity > 0),
        unit_price NUMERIC(10,2) NOT NULL CHECK (unit_price >= 0),
        subtotal NUMERIC(10,2) NOT NULL CHECK (subtotal >= 0),
        CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE
        );

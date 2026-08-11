-- ============================================================
-- SERVICIO DE CATÁLOGOS
-- ============================================================
CREATE TABLE products (
    id            BIGSERIAL NOT NULL,
    restaurant_id BIGSERIAL NOT NULL,
    category_id   BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    unitcode      VARCHAR(3),
    price         DECIMAL(12,2) NOT NULL,
    stock         INTEGER NOT NULL DEFAULT 0,
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_products
        PRIMARY KEY (id),

    CONSTRAINT ck_products_price
        CHECK (price >= 0),

    CONSTRAINT ck_products_stock
        CHECK (stock >= 0)
);

CREATE INDEX idx_products_category_id
    ON products(category_id);

CREATE INDEX idx_products_stock
    ON products(stock);


CREATE TABLE restaurants (
    id       BIGSERIAL NOT NULL,
    name     VARCHAR(255) NOT NULL,
    type     VARCHAR(255) NOT NULL,
    address  VARCHAR(500),
    enabled bool DEFAULT true NOT NULL,
    CONSTRAINT pk_restaurants
            PRIMARY KEY (id)
);
CREATE INDEX idx_restaurants_enabled
    ON restaurants(enabled);

CREATE INDEX idx_restaurants_name
    ON restaurants(name);

CREATE TABLE categories (
    id          BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
        CONSTRAINT pk_categories
           PRIMARY KEY (id)
);


ALTER TABLE products
    ADD CONSTRAINT fk_products_categories
        FOREIGN KEY (category_id)
            REFERENCES categories(id);


ALTER TABLE products
    ADD CONSTRAINT fk_products_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurants(id);
	
--sequences:

CREATE SEQUENCE seq_categories
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE seq_restaurants
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE seq_products
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


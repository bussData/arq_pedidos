
-- ============================================================
-- SERVICIO DE DELIVERY
-- ============================================================

CREATE TABLE deliveries (
    id         BIGSERIAL NOT NULL,
    order_id   BIGSERIAL NOT NULL,
    status     VARCHAR(50) NOT NULL,
    trace_code BIGSERIAL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_deliveries
        PRIMARY KEY (id)
);


CREATE TABLE delivery_drivers (
    id           BIGSERIAL NOT NULL,
    delivery_id  BIGSERIAL NOT NULL,
    user_id      BIGSERIAL NOT NULL,
    vehicle_type VARCHAR(50),
    vehicle_plate VARCHAR(20),
    status       VARCHAR(50),
    phone        VARCHAR(30),
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_delivery_drivers
        PRIMARY KEY (id)
);


CREATE TABLE delivery_status (
    id          BIGSERIAL NOT NULL,
    delivery_id BIGSERIAL NOT NULL,
    status      VARCHAR(50) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_delivery_status
        PRIMARY KEY (id)
);
 

ALTER TABLE delivery_drivers
    ADD CONSTRAINT fk_delivery_drivers_deliveries
    FOREIGN KEY (delivery_id)
    REFERENCES deliveries(id);
 
ALTER TABLE delivery_status
    ADD CONSTRAINT fk_delivery_status_deliveries
    FOREIGN KEY (delivery_id)
    REFERENCES deliveries(id);
	
CREATE INDEX idx_deliveries_order_id
    ON deliveries(order_id);

CREATE INDEX idx_deliveries_status
    ON deliveries(status);

CREATE INDEX idx_deliveries_trace_code
    ON deliveries(trace_code);


CREATE INDEX idx_delivery_drivers_delivery_id
    ON delivery_drivers(delivery_id);

CREATE INDEX idx_delivery_drivers_user_id
    ON delivery_drivers(user_id);

CREATE INDEX idx_delivery_drivers_status
    ON delivery_drivers(status);


CREATE INDEX idx_delivery_status_delivery_id
    ON delivery_status(delivery_id);

CREATE INDEX idx_delivery_status_status
    ON delivery_status(status);
	
--sequences:
	
CREATE SEQUENCE seq_deliveries
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE seq_delivery_drivers
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE seq_delivery_status
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
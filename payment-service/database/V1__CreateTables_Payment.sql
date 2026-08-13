-- ============================================================
-- SERVICIO DE PAGOS
-- ============================================================
CREATE TABLE payments (
                          id             BIGSERIAL NOT NULL,
                          order_id       BIGSERIAL NOT NULL,
                          transaction_id     VARCHAR(255)  ,
                          amount         DECIMAL(12,2) NOT NULL,
                          status         VARCHAR(50) NOT NULL,
                          paid_at        TIMESTAMP,

                          CONSTRAINT pk_payments
                              PRIMARY KEY (id),

                          CONSTRAINT ck_payments_amount
                              CHECK (amount >= 0)
);


CREATE TABLE payment_transactions (
                                      id             BIGSERIAL NOT NULL,
                                      payment_id BIGSERIAL NOT NULL,
                                      transaction_id     VARCHAR(255) ,
                                      amount         DECIMAL(12,2) NOT NULL,
                                      status         VARCHAR(50) NOT NULL,
                                      failure_reason VARCHAR(500),
                                      created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                      CONSTRAINT pk_payment_transactions
                                          PRIMARY KEY (id),

                                      CONSTRAINT ck_payment_transactions_amount
                                          CHECK (amount >= 0)
);



ALTER TABLE payment_transactions
    ADD CONSTRAINT fk_payment_transactions_payments
        FOREIGN KEY (payment_id)
            REFERENCES payments(id);

CREATE INDEX idx_payments_order_id
    ON payments(order_id);

CREATE INDEX idx_payments_status
    ON payments(status);

CREATE INDEX idx_payment_transactions_payment_id
    ON payment_transactions(payment_id);

CREATE INDEX idx_payment_transactions_transaction_id
    ON payment_transactions(transaction_id);

CREATE INDEX idx_payment_transactions_status
    ON payment_transactions(status);

--sequences:

CREATE SEQUENCE seq_payments
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE seq_payment_transactions
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
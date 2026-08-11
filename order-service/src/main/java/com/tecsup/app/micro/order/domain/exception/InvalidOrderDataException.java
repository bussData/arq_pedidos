package com.tecsup.app.micro.order.domain.exception;

/**
 * Excepción cuando los datos del order son inválidos
 */
public class InvalidOrderDataException extends RuntimeException {

    public InvalidOrderDataException(String message) {
        super(message);
    }
}

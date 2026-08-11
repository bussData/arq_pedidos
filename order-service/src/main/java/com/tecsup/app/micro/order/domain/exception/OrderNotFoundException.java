package com.tecsup.app.micro.order.domain.exception;

/**
 * Excepción cuando no se encuentra un producto
 */
public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException(String message) {
        super(message);
    }
    
    public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }
}

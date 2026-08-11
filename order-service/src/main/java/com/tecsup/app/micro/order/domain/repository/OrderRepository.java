package com.tecsup.app.micro.order.domain.repository;

import com.tecsup.app.micro.order.domain.model.Order;

import java.util.List;
import java.util.Optional;

/**
 * Puerto del Repositorio de Producto (Interface)
 * Define el contrato para la persistencia sin depender de la implementación
 * Esta interfaz pertenece al dominio y será implementada en la capa de infraestructura
 */
public interface OrderRepository {
    
    /**
     * Obtiene todos los productos
     */
    List<Order> findAll();
    
    /**
     * Busca un producto por ID
     */
    Optional<Order> findById(Long id);

    /**
     * Guarda un nuevo producto o actualiza uno existente
     */
    Order save(Order order);

    /**
     * Verifica si existe un producto con el ID dado
     */
    boolean existsById(Long id);

    Long getNextOrderNumber();
}

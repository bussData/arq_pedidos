package com.tecsup.app.micro.order.infrastructure.persistence.repository;

import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio JPA de Order
 * Interface de Spring Data JPA para operaciones de persistencia
 */
public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT nextval('order_number_seq')", nativeQuery = true)
    Long getNextOrderId();

}

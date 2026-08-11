package com.tecsup.app.micro.order.infrastructure.persistence.repository;

import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  JpaOrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}

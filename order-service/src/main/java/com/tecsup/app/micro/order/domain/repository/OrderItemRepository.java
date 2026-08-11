package com.tecsup.app.micro.order.domain.repository;

import com.tecsup.app.micro.order.domain.model.OrderItem;

public interface OrderItemRepository {
    OrderItem save(OrderItem orderItem);
}

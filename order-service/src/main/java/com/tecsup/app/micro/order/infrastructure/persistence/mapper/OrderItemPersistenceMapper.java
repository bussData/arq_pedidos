package com.tecsup.app.micro.order.infrastructure.persistence.mapper;

import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemPersistenceMapper {
    OrderItemEntity toEntity(OrderItem orderItem);

    OrderItem toDomain(OrderItemEntity savedEntity);
}

package com.tecsup.app.micro.order.infrastructure.persistence.mapper;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper entre entidades JPA y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {
    
    /**
     * Convierte OrderEntity a Order de dominio
     */
    Order toDomain(OrderEntity entity);
    
    /**
     * Convierte Order de dominio a OrderEntity
     */
    OrderEntity toEntity(Order order);

}

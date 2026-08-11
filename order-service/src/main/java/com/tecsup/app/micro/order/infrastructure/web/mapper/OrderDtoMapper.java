package com.tecsup.app.micro.order.infrastructure.web.mapper;

import com.tecsup.app.micro.order.domain.model.Order;

import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderEntity;
import com.tecsup.app.micro.order.infrastructure.web.dto.CreateOrderRequest;
import com.tecsup.app.micro.order.infrastructure.web.dto.OrderResponse;
import com.tecsup.app.micro.order.infrastructure.web.dto.UpdateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper entre DTOs de presentación y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring",
        uses = {OrderItemDtoMapper.class})
public interface OrderDtoMapper {
    
    /**
     * Convierte CreateProductRequest a Product de dominio
     */
    Order toDomain(CreateOrderRequest request);
    
    /**
     * Convierte UpdateProductRequest a Product de dominio
     */
    Order toDomain(UpdateOrderRequest request);
    
    /**
     * Convierte lista de Products a lista de ProductResponse
     */
    List<OrderResponse> toResponseList(List<Order> orders);

    OrderResponse toResponse(Order createdOrder);

    OrderEntity toEntity(Order order);
}

package com.tecsup.app.micro.order.infrastructure.web.mapper;

import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.infrastructure.web.dto.CreateOrderItemRequest;
import com.tecsup.app.micro.order.infrastructure.web.dto.OrderItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemDtoMapper {

    OrderItem toDomain(CreateOrderItemRequest request);

    OrderItemResponse toResponse(OrderItem orderItem);
}
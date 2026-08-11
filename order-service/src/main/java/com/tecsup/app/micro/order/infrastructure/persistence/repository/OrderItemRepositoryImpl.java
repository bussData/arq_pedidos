package com.tecsup.app.micro.order.infrastructure.persistence.repository;

import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.repository.OrderItemRepository;
import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderItemEntity;
import com.tecsup.app.micro.order.infrastructure.persistence.mapper.OrderItemPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final JpaOrderItemRepository  jpaOrderItemRepository;
    private final OrderItemPersistenceMapper mapper;

    @Override
    public OrderItem save(OrderItem orderItem) {
        log.debug("grabado el item de la orden: "+orderItem.getOrderId());
        OrderItemEntity entity = mapper.toEntity(orderItem);
        OrderItemEntity savedEntity = jpaOrderItemRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}

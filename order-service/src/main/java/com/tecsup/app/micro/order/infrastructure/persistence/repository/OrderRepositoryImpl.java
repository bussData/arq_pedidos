package com.tecsup.app.micro.order.infrastructure.persistence.repository;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.repository.OrderRepository;
import com.tecsup.app.micro.order.infrastructure.persistence.entity.OrderEntity;
import com.tecsup.app.micro.order.infrastructure.persistence.mapper.OrderPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio de Order (Adaptador)
 * Conecta el dominio con la infraestructura de persistencia usando MapStruct
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {
    
    private final JpaOrderRepository jpaOrderRepository;
    private final OrderPersistenceMapper mapper;
    
    @Override
    public List<Order> findAll() {
        log.debug("Finding all orders");
        //return mapper.toDomainList(jpaOrderRepository.findAll());
        return null;
    }
    
    @Override
    public Optional<Order> findById(Long id) {
        log.debug("Finding product by id: {}", id);
        return jpaOrderRepository.findById(id)
                .map(mapper::toDomain);
    }


    @Override
    public Order save(Order order) {
        log.debug("Saving order: {}", order.getOrderNumber());
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity savedEntity = jpaOrderRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }


    @Override
    public Long getNextOrderNumber() {
        return jpaOrderRepository.getNextOrderId();
    }

}

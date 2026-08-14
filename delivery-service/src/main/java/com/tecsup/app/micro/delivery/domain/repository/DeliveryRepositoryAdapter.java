package com.tecsup.app.micro.delivery.domain.repository;

import com.tecsup.app.micro.delivery.domain.model.Delivery;
import com.tecsup.app.micro.delivery.infraestructure.persistence.DeliveryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryAdapter implements DeliveryRepository{
    private final DeliveryJpaRepository repository;


    @Override
    public Delivery save(Delivery delivery) {
        DeliveryEntity entity = DeliveryEntity.builder()
                .id(delivery.getId()==null ? null: Long.valueOf(delivery.getId()))
                .orderId(delivery.getOrderId())
                .status(delivery.getStatus())
                .traceCode(delivery.getTraceCode())
                .createdAt(delivery.getCreatedAt())
                .build();
        DeliveryEntity saved = repository.save(entity);
        return Delivery.builder()
                .id(saved.getId().toString())
                .orderId(saved.getOrderId())
                .traceCode(saved.getTraceCode())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .build();

    }
}

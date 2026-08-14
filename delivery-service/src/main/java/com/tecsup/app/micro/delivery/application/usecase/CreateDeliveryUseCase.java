package com.tecsup.app.micro.delivery.application.usecase;

import com.tecsup.app.micro.delivery.domain.model.Delivery;
import com.tecsup.app.micro.delivery.domain.repository.DeliveryRepository;
import com.tecsup.app.micro.delivery.infraestructure.event.KafkaEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateDeliveryUseCase {

    private final DeliveryRepository deliveryRepository;

    public Delivery create(String orderId, String status) {

        Delivery record = Delivery.create(Long.valueOf(orderId),status);
        Delivery saved = deliveryRepository.save(record);
        log.info("Traza de delivery creada: {}", saved.getId());
        return saved;
    }
}

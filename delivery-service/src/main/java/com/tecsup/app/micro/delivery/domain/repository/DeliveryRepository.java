package com.tecsup.app.micro.delivery.domain.repository;

import com.tecsup.app.micro.delivery.domain.model.Delivery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeliveryRepository {

    Delivery save(Delivery record);
}

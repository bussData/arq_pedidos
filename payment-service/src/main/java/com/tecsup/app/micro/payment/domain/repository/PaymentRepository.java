package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findById(String id);

    Optional<Payment> findByOrderId(String orderId);
}

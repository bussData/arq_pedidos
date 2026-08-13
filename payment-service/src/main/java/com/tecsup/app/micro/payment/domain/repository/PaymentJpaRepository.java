package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.infrastructure.persitence.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  PaymentJpaRepository  extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByOrderId(Long orderId);
}

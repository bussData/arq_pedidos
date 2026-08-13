package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.infrastructure.persitence.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository repository;

    @Override
    public Payment save(Payment payment) {

        PaymentEntity entity = PaymentEntity.builder()
                .id(payment.getId() == null ? null : Long.valueOf(payment.getId()))
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .status(payment.getStatus())
                .paidAt(payment.getPaidAt())
                .build();

        PaymentEntity saved = repository.save(entity);

        return Payment.builder()
                .id(saved.getId().toString())
                .orderId(saved.getOrderId())
                .amount(saved.getAmount())
                .transactionId(saved.getTransactionId())
                .status(saved.getStatus())
                .paidAt(saved.getPaidAt())
                .build();
    }

    @Override
    public Optional<Payment> findById(String id) {

        return repository.findById(Long.valueOf(id))
                .map(this::toDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(String orderId) {

        return repository.findByOrderId(Long.valueOf(orderId))
                .map(this::toDomain);
    }

    private Payment toDomain(PaymentEntity entity) {

        return Payment.builder()
                .id(entity.getId().toString())
                .orderId(entity.getOrderId())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .paidAt(entity.getPaidAt())
                .build();
    }
}

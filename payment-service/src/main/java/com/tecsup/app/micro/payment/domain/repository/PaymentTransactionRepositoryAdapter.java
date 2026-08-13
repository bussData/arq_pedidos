package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import com.tecsup.app.micro.payment.domain.model.PaymentTransaction;
import com.tecsup.app.micro.payment.infrastructure.persitence.PaymentTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentTransactionRepositoryAdapter implements PaymentTransactionRepository {

    private final PaymentTransactionJpaRepository repository;

    public PaymentTransaction save(PaymentTransaction paymentTrx) {
        PaymentTransactionEntity entity = PaymentTransactionEntity.builder()
                .paymentId(paymentTrx.getPaymentId())
                .amount(paymentTrx.getAmount())
                .transactionId(paymentTrx.getTransactionId())
                .status(PaymentStatus.valueOf(paymentTrx.getStatus()))
                .failureReason(paymentTrx.getFailureReason()!=null?paymentTrx.getFailureReason():null)
                .createdAt(paymentTrx.getCreatedAt())
                .build();

        PaymentTransactionEntity saved = repository.save(entity);
        
        return PaymentTransaction.builder()
                .id(saved.getId().toString())
                .paymentId(saved.getPaymentId())
                .transactionId(saved.getTransactionId())
                .amount(saved.getAmount())
                .status(String.valueOf(saved.getStatus()))
                .createdAt(saved.getCreatedAt())
                .build();
    }

}

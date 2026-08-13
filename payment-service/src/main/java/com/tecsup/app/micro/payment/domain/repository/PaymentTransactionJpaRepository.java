package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.infrastructure.persitence.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentTransactionJpaRepository  extends JpaRepository<PaymentTransactionEntity, Long> {

    @Query(value = "SELECT nextval('seq_payment_transactions')", nativeQuery = true)
    Long getNextPaymentTransactionNumber();
}

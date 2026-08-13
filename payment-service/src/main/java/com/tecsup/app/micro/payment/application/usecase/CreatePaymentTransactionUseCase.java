package com.tecsup.app.micro.payment.application.usecase;

import com.tecsup.app.micro.payment.domain.model.PaymentTransaction;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import com.tecsup.app.micro.payment.domain.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePaymentTransactionUseCase {

    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransaction create(Long paymentId, BigDecimal amount, String failureReason,String failureMessage,
                                     String transactionId) {

        PaymentTransaction trx = PaymentTransaction.create( paymentId, amount, failureReason,failureMessage, transactionId);
        PaymentTransaction saved = paymentTransactionRepository.save(trx);
        log.info("Traza de trx en Payment_transaction created: {}", saved.getId());

        return saved;
    }
}

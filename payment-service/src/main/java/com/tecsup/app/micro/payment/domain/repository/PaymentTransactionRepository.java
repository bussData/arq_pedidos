package com.tecsup.app.micro.payment.domain.repository;

import com.tecsup.app.micro.payment.domain.model.PaymentTransaction;
import org.springframework.stereotype.Component;

@Component
public interface PaymentTransactionRepository {
    PaymentTransaction save(PaymentTransaction paymentTrx);

}

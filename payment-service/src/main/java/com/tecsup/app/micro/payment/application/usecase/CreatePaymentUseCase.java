package com.tecsup.app.micro.payment.application.usecase;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePaymentUseCase {

    private final PaymentRepository paymentRepository;

    public Payment registrarPayment(Long id, BigDecimal amount) {

        Payment pago = Payment.create(id , amount);
        Payment saved = paymentRepository.save(pago);

        log.info("Payment created: {}", saved.getId());

        return saved;

    }
    
    public Payment getPaymentById(String id) {
        Payment pago = paymentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Payment not found with id: " + id));
        return pago;
    }
}

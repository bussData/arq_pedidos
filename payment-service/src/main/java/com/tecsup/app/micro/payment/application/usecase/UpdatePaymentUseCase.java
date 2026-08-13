package com.tecsup.app.micro.payment.application.usecase;

import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdatePaymentUseCase {
    private final PaymentRepository paymentRepository;

    public Payment actualizarEstado(Payment paymentActual,  String transactionId) {

        if(transactionId !=null){
            paymentActual.approve(transactionId);
        }else{
            paymentActual.reject();
        }
        Payment saved = paymentRepository.save(paymentActual);
        log.info("Payment actualizado: {}", saved.getId());

        return saved;
    }

}

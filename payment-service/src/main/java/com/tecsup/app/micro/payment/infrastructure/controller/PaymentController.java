package com.tecsup.app.micro.payment.infrastructure.controller;


import com.tecsup.app.micro.payment.application.command.CreatePaymentCommand;
import com.tecsup.app.micro.payment.application.command.PaymentCommandHandler;
import com.tecsup.app.micro.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentCommandHandler paymentCommandHandler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@RequestBody CreatePaymentCommand command) {

        log.info("[PAYMENT] Solicitud recibida para crear un pago");
        log.info("OrderId: {}", command.getOrderId());
        log.info("Amount: {}", command.getAmount());

        Payment payment = paymentCommandHandler.createPayment(command);

        log.info("[PAYMENT] Pago creado correctamente con id {}", payment.getId());

        return payment;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Payment> findPaymentById(@PathVariable String id) {
        Payment payment = paymentCommandHandler.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }
}

package com.tecsup.app.micro.payment.application.command;

import com.tecsup.app.micro.events.OrderCreatedEvent;
import com.tecsup.app.micro.events.PaymentApprovedEvent;
import com.tecsup.app.micro.events.PaymentRejectedEvent;
import com.tecsup.app.micro.payment.application.usecase.CreatePaymentTransactionUseCase;
import com.tecsup.app.micro.payment.application.usecase.CreatePaymentUseCase;
import com.tecsup.app.micro.payment.application.usecase.UpdatePaymentUseCase;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import com.tecsup.app.micro.payment.domain.model.PaymentTransaction;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import com.tecsup.app.micro.payment.infrastructure.event.KafkaEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCommandHandler {

    private final KafkaEventPublisher kafkaEventPublisher;
    private final Random random = new Random();
    private final CreatePaymentUseCase createPaymentUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;
    private final CreatePaymentTransactionUseCase createPaymentTransactionUseCase;


    public Payment createPayment(CreatePaymentCommand command)  {

        Payment payment =
                createPaymentUseCase.registrarPayment(command.getOrderId(), command.getAmount());
        try {
            Thread.sleep(1000 + random.nextInt(2000)); // 1-3 segundos

            boolean approved = random.nextInt(100) < 60;

            if (approved) {

                log.info("Pago aprobado.");
                 // Generas un codigo de transaccion
                String transactionId = "tx-" + UUID.randomUUID();


                // Generar el evento
                PaymentApprovedEvent processedEvent = new PaymentApprovedEvent(
                        Long.valueOf(payment.getOrderId()),
                        transactionId,
                        payment.getAmount(),
                        LocalDateTime.now());

                kafkaEventPublisher.publish(processedEvent);

                log.info("[PAYMENT] Pago procesado exitosamente");

                updatePaymentUseCase.actualizarEstado(payment,transactionId);

                PaymentTransaction payTrx =
                        createPaymentTransactionUseCase.create(Long.valueOf(payment.getId()),
                                payment.getAmount(), null,null,transactionId);


            } else {
                log.warn("❌ [PAYMENT] El pago falló para enrollment ID: {}", payment.getOrderId());

                // TO DO


                PaymentRejectedEvent failedEvent = new PaymentRejectedEvent(
                        Long.valueOf(payment.getOrderId()),
                        "PAYMENT_DECLINED",
                        "El pago fue rechazado por el proveedor, saldo insuficiente.",
                        LocalDateTime.now()
                );


                // Publica el evento fallido
                this.kafkaEventPublisher.publish(failedEvent);

                log.warn("📨 [PAYMENT] Evento PaymentFailed publicado");


                updatePaymentUseCase.actualizarEstado(payment,null);
                PaymentTransaction payTrx =
                        createPaymentTransactionUseCase.create(Long.valueOf(payment.getId()),
                                 payment.getAmount(),
                                failedEvent.getReason(),
                                failedEvent.getErrorCode(),
                                "0");
            }

        } catch (InterruptedException e) {
            log.error("[PAYMENT] Error procesando pago", e);
        }
        return payment;
    }

    public Payment getPaymentById(String id) {
        Payment payment =
                createPaymentUseCase.getPaymentById(id);
        return payment;
    }
}

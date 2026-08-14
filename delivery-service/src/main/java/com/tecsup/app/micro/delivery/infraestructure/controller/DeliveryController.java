package com.tecsup.app.micro.delivery.infraestructure.controller;

import com.tecsup.app.micro.delivery.application.eventhandler.DeliveryEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryEventHandler deliveryEventHandler;

}

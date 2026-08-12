package com.tecsup.app.micro.order.infrastructure.web.controller;


import com.tecsup.app.micro.order.application.service.OrderApplicationService;
import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.infrastructure.web.dto.CreateOrderRequest;
import com.tecsup.app.micro.order.infrastructure.web.dto.OrderResponse;
import com.tecsup.app.micro.order.infrastructure.web.mapper.OrderDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Productos
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    
    private final OrderApplicationService orderApplicationService;
    private final OrderDtoMapper orderDtoMapper;
    
    /**
     * Obtiene todos las ordenes
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("REST request to get all orders");
       // List<Order> orders = orderApplicationService.getAllOrders();
        //return ResponseEntity.ok(orderDtoMapper.toResponseList(orders));
        return null;
    }
    /*


    /**
     * Crea una nueva orden (solo ADMIN)
     */
    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAnyRole('RESTAURANT_MANAGER','CLIENT','ADMIN')")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request,
                                                     @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        Order order = orderDtoMapper.toDomain(request);
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        Order validateOrder = orderApplicationService.validateOrder(order, email, authorizationHeader);
        Order createdOrder = orderApplicationService.createOrder(validateOrder, authorizationHeader);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderDtoMapper.toResponse(createdOrder));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ORder Service running with Clean Architecture!");
    }

}

package com.tecsup.app.micro.catalog.infraestructure.web.controller;

import com.tecsup.app.micro.catalog.application.service.CatalogApplicationService;
import com.tecsup.app.micro.catalog.application.service.ProductApplicationService;
import com.tecsup.app.micro.catalog.domain.model.Product;
import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import com.tecsup.app.micro.catalog.infraestructure.client.mapper.UserDtoMapper;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.ProductResponse;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.RestaurantResponse;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.UpdateProductRequest;
import com.tecsup.app.micro.catalog.infraestructure.web.mapper.ProductDtoMapper;
import com.tecsup.app.micro.catalog.infraestructure.web.mapper.RestaurantDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogs")
@RequiredArgsConstructor
@Slf4j
public class CatalogController {

    private final CatalogApplicationService catalogApplicationService;
    private final ProductApplicationService productApplicationService;
    private final RestaurantDtoMapper restaurantDtoMapper;
    private final ProductDtoMapper productDtoMapper;


    @GetMapping
    @PreAuthorize("isAuthenticated() and hasAnyRole('RESTAURANT_MANAGER', 'CLIENT','ADMIN')")
    public ResponseEntity<List<RestaurantResponse>> getAllProducts() {
        log.info("REST request to get all products");
        List<Restaurant> restaurants = catalogApplicationService.getAllProducts();
        return ResponseEntity.ok(restaurantDtoMapper.toResponseList(restaurants));
    }


    @GetMapping("/{restaurantId}")
    @PreAuthorize("isAuthenticated() and hasAnyRole('RESTAURANT_MANAGER','CLIENT','ADMIN')")
    public ResponseEntity<RestaurantResponse> getAllProductsFromRestaurant(
            @PathVariable Long restaurantId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Extraer JWT del header
        String jwtToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
        } else {
            log.warn("No Authorization header with Bearer token found for product retrieval");
        }

        log.info("getAllProductsFromRestaurant");
        Optional<Restaurant> restaurante = catalogApplicationService.getAllProductsbyRestaurant(
                restaurantId,jwtToken);

        return   restaurante
                .map(restaurantDtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    @PreAuthorize("isAuthenticated() and hasAnyRole('RESTAURANT_MANAGER','CLIENT','ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateProductRequest request) {
        log.info("REST request to update product with id: {}", productId);
        Product product = productDtoMapper.toDomain(request);
        Product updatedProduct = productApplicationService.updateProduct(productId, product);
        return ResponseEntity.ok(productDtoMapper.toResponse(updatedProduct));
    }

    @GetMapping("/all/products/{productId}")
    @PreAuthorize("isAuthenticated() and hasAnyRole('RESTAURANT_MANAGER','CLIENT','ADMIN')")
    public ResponseEntity<ProductResponse> getAllProducts(
            @PathVariable Long productId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Extraer JWT del header
        String jwtToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
        } else {
            log.warn("No Authorization header with Bearer token found for product retrieval");
        }

        log.info("getAllProductsFromRestaurant");
        Optional <Product> producto = productApplicationService.getProduct(productId,jwtToken);

        return   producto
                .map(productDtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

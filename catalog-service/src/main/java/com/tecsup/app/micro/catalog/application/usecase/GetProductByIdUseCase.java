package com.tecsup.app.micro.catalog.application.usecase;

import com.tecsup.app.micro.catalog.domain.model.Category;
import com.tecsup.app.micro.catalog.domain.model.Product;
import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import com.tecsup.app.micro.catalog.domain.model.User;
import com.tecsup.app.micro.catalog.domain.repository.CategoryRepository;
import com.tecsup.app.micro.catalog.domain.repository.ProductRepository;
import com.tecsup.app.micro.catalog.domain.repository.RestaurantRepository;
import com.tecsup.app.micro.catalog.infraestructure.client.UserClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Caso de uso: Obtener producto por ID
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GetProductByIdUseCase {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    private final UserClient userClient;

    public Optional<Product> execute(Long id, String jwtToken) {  // NUEVO PARAMETRO
        log.debug("Executing GetProductByIdUseCase for id: {}", id);

        Product prod = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: "+id));

        Category category = categoryRepository.findById(prod.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categoría no encontrada: " + prod.getCategoryId()));

        prod.setCategory(category);

        Restaurant restaurant = restaurantRepository.findById(prod.getRestaurantId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Restaurante no encontrado: " + prod.getRestaurantId()));
        prod.setRestaurant(restaurant);

        return Optional.of(prod);
    }

}

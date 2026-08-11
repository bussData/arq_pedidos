package com.tecsup.app.micro.catalog.application.service;

import com.tecsup.app.micro.catalog.application.usecase.*;
import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogApplicationService {

    private final GetAllProductsbyRestaurantUseCase getAllProductsbyRestaurantUseCase;
    private final GetAllRestaurantsUseCase getAllRestaurantsUseCase;

    @Transactional(readOnly = true)
    public List<Restaurant> getAllProducts() {
        return getAllRestaurantsUseCase.execute();
    }


    @Transactional(readOnly = true)
    public Optional<Restaurant> getAllProductsbyRestaurant(Long restaurantId, String jwtToken){
        return getAllProductsbyRestaurantUseCase.execute(restaurantId, jwtToken);
    }
}

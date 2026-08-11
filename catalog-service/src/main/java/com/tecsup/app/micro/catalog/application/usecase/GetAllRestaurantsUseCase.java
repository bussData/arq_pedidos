package com.tecsup.app.micro.catalog.application.usecase;

import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import com.tecsup.app.micro.catalog.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllRestaurantsUseCase {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> execute()
    {
        return restaurantRepository.findAll();
    }
}

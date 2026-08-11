package com.tecsup.app.micro.catalog.domain.repository;

import com.tecsup.app.micro.catalog.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
}

package com.tecsup.app.micro.catalog.infraestructure.persistence.repository;

import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import com.tecsup.app.micro.catalog.domain.repository.RestaurantRepository;
import com.tecsup.app.micro.catalog.infraestructure.persistence.mapper.RestaurantPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final RestaurantPersistenceMapper mapper;

    @Override
    public List<Restaurant> findAll() {
        log.debug("Finding all restaurantesss");
        return mapper.toDomainList(jpaRestaurantRepository.findAll());
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        log.debug("Finding restaurante by id: {}", id);
        return jpaRestaurantRepository.findById(id)
                .map(mapper::toDomain);
    }
}

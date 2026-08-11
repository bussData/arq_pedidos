package com.tecsup.app.micro.catalog.infraestructure.persistence.repository;


import com.tecsup.app.micro.catalog.infraestructure.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRestaurantRepository  extends JpaRepository<RestaurantEntity, Long> {

    List<RestaurantEntity> findByName(String name);
}

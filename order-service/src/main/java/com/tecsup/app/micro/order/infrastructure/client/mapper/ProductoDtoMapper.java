package com.tecsup.app.micro.order.infrastructure.client.mapper;

import com.tecsup.app.micro.order.domain.model.Category;
import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.domain.model.Restaurant;
import com.tecsup.app.micro.order.infrastructure.client.dto.CategoryDTO;
import com.tecsup.app.micro.order.infrastructure.client.dto.ProductoDTO;
import com.tecsup.app.micro.order.infrastructure.client.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

@Mapper(componentModel = "spring")
public interface ProductoDtoMapper {

    //ProductoDTO toDomain(ProductoDTO productoDTO);

    Product toDomain(ProductoDTO productoDTO);

    ProductoDTO toDto(Product producto);

    Category toDomain(CategoryDTO categoryDTO);
    CategoryDTO toDto(Category category);

    Restaurant toDomain(RestaurantDTO restaurantDTO);
    RestaurantDTO toDto(Restaurant restaurant);
}

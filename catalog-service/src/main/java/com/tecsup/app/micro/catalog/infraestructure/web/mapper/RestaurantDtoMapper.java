package com.tecsup.app.micro.catalog.infraestructure.web.mapper;

import com.tecsup.app.micro.catalog.domain.model.Category;
import com.tecsup.app.micro.catalog.domain.model.Product;
import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.CategoryResponse;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.ProductResponse;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.RestaurantResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantDtoMapper {

    RestaurantResponse toResponse(Restaurant restaurant);
    List<RestaurantResponse> toResponseList(List<Restaurant> restaurants);

    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toProductResponseList(List<Product> products);

    CategoryResponse toCategoryResponse(Category category);
}

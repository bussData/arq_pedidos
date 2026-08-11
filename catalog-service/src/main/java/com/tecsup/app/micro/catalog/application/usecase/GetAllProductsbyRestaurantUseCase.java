package com.tecsup.app.micro.catalog.application.usecase;

import com.tecsup.app.micro.catalog.domain.model.Category;
import com.tecsup.app.micro.catalog.domain.model.Product;
import com.tecsup.app.micro.catalog.domain.model.Restaurant;
import com.tecsup.app.micro.catalog.domain.repository.CategoryRepository;
import com.tecsup.app.micro.catalog.domain.repository.ProductRepository;
import com.tecsup.app.micro.catalog.domain.repository.RestaurantRepository;
import com.tecsup.app.micro.catalog.infraestructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllProductsbyRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //private final UserClient userClient;

    public Optional<Restaurant> execute(Long restaurantId, String jwtToken){
        log.debug("Ejecutando GetAllProductsbyRestaurantUseCase para restaurantId: {}", restaurantId);
        //return restaurantRepository.findById(restaurantId);
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> {
                    List<Product> products = productRepository.findByRestaurantId(restaurantId);

                    //busca toda la relacion de ids de categorias en una sola barrida:
                    List<Long> categoryIds = products.stream()
                            .map(Product::getCategoryId)
                            .distinct()
                            .toList();
                    List<Category> categories =
                            categoryRepository.findAllById(categoryIds);

                    Map<Long, Category> categoryMap = categories.stream()
                            .collect(Collectors.toMap(
                                    Category::getId,
                                    category -> category
                            ));

                    products.forEach(product -> {
                        categoryRepository.findById(product.getCategoryId())
                                .ifPresent(product::setCategory);
                    });
                    restaurant.setProducts(products);
                    return restaurant;
                });
    }
}

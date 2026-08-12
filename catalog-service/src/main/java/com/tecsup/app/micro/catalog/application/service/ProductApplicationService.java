package com.tecsup.app.micro.catalog.application.service;

import com.tecsup.app.micro.catalog.application.usecase.*;
import com.tecsup.app.micro.catalog.domain.model.Product;
import com.tecsup.app.micro.catalog.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de Aplicación de Producto
 * Orquesta los casos de uso y maneja las transacciones
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductApplicationService {
    
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;

    @Transactional
    public Product updateProduct(Long id, Product product) {
        return updateProductUseCase.execute(id, product);
    }

    public Optional<Product> getProduct(Long id, String jwtToken) {return getProductByIdUseCase.execute(id,jwtToken);}

}

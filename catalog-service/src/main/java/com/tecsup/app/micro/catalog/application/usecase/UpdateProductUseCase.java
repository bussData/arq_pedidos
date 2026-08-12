package com.tecsup.app.micro.catalog.application.usecase;

import com.tecsup.app.micro.catalog.domain.model.Product;
import com.tecsup.app.micro.catalog.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Caso de uso: Actualizar un producto existente
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateProductUseCase {
    
    private final ProductRepository productRepository;
    
    public Product execute(Long id, Product productDetails) {
        log.debug("Executing UpdateProductUseCase for id: {}", id);
        
        // Verificar que el producto existe
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no existe: "+String.valueOf(id)));

        // Actualizar campos
        int stockActual = existingProduct.getStock();
        int cantAUsar = productDetails.getStock();

        existingProduct.setStock(stockActual-cantAUsar);
        
        // Guardar cambios
        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product updated successfully with id: {}", updatedProduct.getId());
        
        return updatedProduct;
    }
}

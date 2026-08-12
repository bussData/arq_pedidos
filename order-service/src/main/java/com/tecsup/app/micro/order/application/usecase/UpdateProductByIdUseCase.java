package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.exception.InvalidOrderDataException;
import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.infrastructure.client.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateProductByIdUseCase {

    private final ProductClient productClient;
    public Product execute(Long productId, int quantity , String token) {
        log.debug("Inicio actualizacion de stock de producto por id:", productId);

        Product producto;
        try {
            producto = productClient.updateProductStockById(productId, quantity, token);
            if(producto==null){
                throw new RuntimeException("No se proceso el update de stock");
            }
        } catch (Exception e) {
            throw new InvalidOrderDataException("Producto con el id " + productId + " no actualizo el stock");
        }
        return producto;
    }

}

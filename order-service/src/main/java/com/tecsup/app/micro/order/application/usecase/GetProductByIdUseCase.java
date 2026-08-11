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
public class GetProductByIdUseCase {

    private final ProductClient productClient;

    public Product execute(Long productId, String jwtToken)
    {
        log.debug("Inicio busqueda de producto por id:",productId);

        try {
            Product producto = productClient.getProductById(productId, jwtToken.replace("Bearer ",""));
            log.info("Producto encontrado con el id {}", producto.getId());

            if (producto == null) {
                log.warn("Producto no encontrado con el id {}", productId);
                throw new InvalidOrderDataException("Producto con el id " + productId + " no encontrado");
            }
            return producto;

        } catch (Exception e) {
            throw new InvalidOrderDataException("Producto con el id " + productId + " no encontrado");
        }
    }

}

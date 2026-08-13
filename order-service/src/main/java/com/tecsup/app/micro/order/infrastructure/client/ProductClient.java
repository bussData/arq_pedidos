package com.tecsup.app.micro.order.infrastructure.client;

import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.infrastructure.client.dto.ProductoDTO;
import com.tecsup.app.micro.order.infrastructure.client.dto.UpdateProductRequest;
import com.tecsup.app.micro.order.infrastructure.client.mapper.ProductoDtoMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductClient {

    private final RestTemplate restTemplate;
    private final ProductoDtoMapper productoDtoMapper;

    @Value("${catalog.service.url}")
    private String catalogServiceUrl;

    @CircuitBreaker(name = "orderService", fallbackMethod = "getProductFallback")
    @Retry(name = "orderService")
    public Product getProductById(Long id, String jwtToken){
        log.info("Invocando rest del product-service para validar el producto con jwt");

        String url = this.catalogServiceUrl+"/api/catalogs/all/products/"+id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (jwtToken != null && !jwtToken.isEmpty()) {
            headers.setBearerAuth(jwtToken);
        } else {
            log.warn("No JWT token provided for Product Service call");
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try{
            ResponseEntity<ProductoDTO> response = this.restTemplate.exchange(
                    url, HttpMethod.GET, entity, ProductoDTO.class);

            ProductoDTO productoDTO = response.getBody();

            log.info("Producto encontrado con jwt para el id {}",response.getBody().getId());
            return productoDtoMapper.toDomain(productoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el producto {}",e.getMessage());
            throw new RuntimeException("Error al obtener el producto con jwt "+ e.getMessage());
        }
    }

    public Product getProductFallback(Long id, String jwtToken, Throwable throwable){
        log.warn("FALLBACK: Product Service no disponible para productId: {}. Razón: {}",
                id, throwable.getMessage());

        return new Product(id, "Producto no disponible", new BigDecimal(0), 0);

    }

    public Product updateProductStockById(Long id, int quantity, String jwtToken){
        log.info("Invocando rest del product-service para actualizar el stock del producto con jwt");

        String url = this.catalogServiceUrl+"/api/catalogs/"+id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (jwtToken != null && !jwtToken.isEmpty()) {
            headers.setBearerAuth(jwtToken);
        } else {
            log.warn("No JWT token provided for Product Service call");
        }

        UpdateProductRequest body = new UpdateProductRequest(quantity);
        HttpEntity<UpdateProductRequest> entity = new HttpEntity<>(body,headers);

        try{
            ResponseEntity<ProductoDTO> response = this.restTemplate.exchange(
                    url, HttpMethod.PUT, entity, ProductoDTO.class);

            ProductoDTO productoDTO = response.getBody();

            log.info("Producto encontrado con jwt para el id {}",response.getBody().getId());
            return productoDtoMapper.toDomain(productoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el producto {}",e.getMessage());
            throw new RuntimeException("Error al obtener el producto con jwt "+ e.getMessage());
        }
    }

}

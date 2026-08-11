package com.tecsup.app.micro.catalog.domain.model;


import lombok.*;
import com.tecsup.app.micro.catalog.domain.model.Product;
import java.util.List;
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String type;
    private String address;

    private List<Product> products;
}


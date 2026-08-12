package com.tecsup.app.micro.order.infrastructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Long id;
    private String name;
    private String type;
    private String address;

}

package com.tecsup.app.micro.order.infrastructure.client.dto;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public  UserDTO(Long id, String name, String email ) {
         this.id= id;
         this.name= name;
         this.email= email;
    }
}

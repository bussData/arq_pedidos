package com.tecsup.app.micro.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCreateEvent  extends DomainEvent {

    private  Long orderId;
    private  String traceCode;
    private  String status;
    private LocalDateTime timestamp;


    @Override
    public String getKey(){ return orderId.toString();}
}

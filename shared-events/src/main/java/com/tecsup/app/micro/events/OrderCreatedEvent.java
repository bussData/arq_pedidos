package com.tecsup.app.micro.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor(force = true)
public class OrderCreatedEvent extends DomainEvent {

    private final String id;
    private final String userId;
    private final String status;
    @Override
    public String getKey(){ return this.id;}

}
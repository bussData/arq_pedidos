package com.tecsup.app.micro.delivery.shared.infraestructure.eventsourcing;


import com.tecsup.app.micro.events.DomainEvent;

import java.util.List;

public interface EventStore {

    void save(String aggregateId, DomainEvent event);

    List<DomainEvent> getEvents(String aggregateId);

}

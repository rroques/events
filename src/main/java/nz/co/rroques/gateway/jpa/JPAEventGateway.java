package nz.co.rroques.gateway.jpa;

import nz.co.rroques.domain.Event;
import nz.co.rroques.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPAEventGateway implements EventGateway {

    private final EventEntityRepository eventEntityRepository;

    @Autowired
    public JPAEventGateway(EventEntityRepository eventEntityRepository) {
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public void saveEvent(Event event) {
        EventEntity eventEntity = new EventEntity(event.getName());
        eventEntityRepository.save(eventEntity);
    }
}

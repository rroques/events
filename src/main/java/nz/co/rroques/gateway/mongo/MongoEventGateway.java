package nz.co.rroques.gateway.mongo;

import nz.co.rroques.domain.Event;
import nz.co.rroques.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MongoEventGateway implements EventGateway {

    private final EventDocumentRepository eventDocumentRepository;

    @Autowired
    public MongoEventGateway(EventDocumentRepository eventDocumentRepository) {
        this.eventDocumentRepository = eventDocumentRepository;
    }

    @Override
    public void saveEvent(Event event) {
        EventDocument eventDocument = new EventDocument(event.getName());
        eventDocumentRepository.save(eventDocument);
    }
}

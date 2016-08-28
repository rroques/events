package nz.co.rroques.gateway.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDocumentRepository extends MongoRepository<EventDocument, Long> {
}

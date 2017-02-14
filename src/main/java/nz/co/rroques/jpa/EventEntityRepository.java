package nz.co.rroques.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventEntityRepository extends CrudRepository<EventEntity, Long> {
}
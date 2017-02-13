package nz.co.rroques.jpa;

import javax.persistence.*;

@Entity
@Table(name = "EVENTS")
public class EventEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "event_id_sequence")
    @SequenceGenerator(name="event_id_sequence", sequenceName="event_id_sequence", allocationSize=1)
    private long id;

    @Column
    private String name;

    protected EventEntity() {}

    public EventEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s']", id, name);
    }

}

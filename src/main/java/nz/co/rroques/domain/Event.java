package nz.co.rroques.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "event_id_sequence")
    @SequenceGenerator(name="event_id_sequence", sequenceName="event_id_sequence", allocationSize=1)
    private long id;

    @Column
    @NotNull
    @Pattern(regexp = "[^\\s]+")
    private String name;

    protected Event() {}

    public Event(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s']", id, name);
    }

}

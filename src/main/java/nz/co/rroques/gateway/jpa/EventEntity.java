package nz.co.rroques.gateway.jpa;

import javax.persistence.*;

@Entity
@Table(name = "EVENTS")
public class EventEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

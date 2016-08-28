package nz.co.rroques.gateway.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Document(collection = "T_BOOK")
public class EventDocument {

    @Id
    private long id;

    @Field("name")
    private String name;

    protected EventDocument() {}

    public EventDocument(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s']", id, name);
    }

}

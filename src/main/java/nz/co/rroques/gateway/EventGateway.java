package nz.co.rroques.gateway;

import nz.co.rroques.domain.Event;

public interface EventGateway {

    void saveEvent(Event event);
}

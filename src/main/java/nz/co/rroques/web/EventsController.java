package nz.co.rroques.web;

import nz.co.rroques.domain.Event;
import nz.co.rroques.domain.EventGateway;
import nz.co.rroques.web.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EventsController {

    private final EventGateway eventGateway;

    @Autowired
    public EventsController(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @RequestMapping(
            name = "/events",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody EventView eventView, BindingResult results) {
        if (results.hasErrors()) {
            throw new ValidationException(results);
        }
        eventGateway.saveEvent(new Event(eventView.getName()));
    }

}

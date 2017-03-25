package nz.co.rroques.web;

import nz.co.rroques.domain.Event;
import nz.co.rroques.jpa.EventRepository;
import nz.co.rroques.web.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EventsController {

    private final EventRepository eventRepository;

    @Autowired
    public EventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping(
            name = "/events",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@Valid @RequestBody Event event, BindingResult results) {
        if (results.hasErrors()) {
            throw new ValidationException(results);
        }
        return eventRepository.save(event);
    }

    @RequestMapping(
            name = "/events",
            params = {"size", "page"},
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<Event> loadEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @RequestMapping(
            name = "/events",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Event> loadEvents() {
        return eventRepository.findAll();
    }

}

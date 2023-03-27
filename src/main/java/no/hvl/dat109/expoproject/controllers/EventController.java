package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.interfaces.controllers.IEventController;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/event")
public class EventController implements IEventController {
    private IEventService es;
    public EventController (EventService es){
        this.es = es;
    }
    @Override
    @GetMapping("/admin")
    public List<Event> getEventsByAdmin(@RequestParam String username) {
        return es.getEventsForUsername(username);
    }
}

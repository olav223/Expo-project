package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.interfaces.controllers.IAdminController;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController implements IAdminController {

    private final IUserService as;
    private final IEventService es;
    private final IVoteService vs;

    public AdminController(UserService as, EventService es, VoteService vs) {
        this.as = as;
        this.es = es;
        this.vs = vs;
    }

    @Override
    @GetMapping("/generate")
    public void generateVoteCodes(int nrOfCodes) {

    }
    @GetMapping("/events")
    public List<Event> getEventsByUsername(@RequestParam String username) {
        return es.getEventsForUsername(username);
    }

    @Override
    @PostMapping("/user")
    public void postAddUser(User user, int eventID) {

    }

    @Override
    @DeleteMapping("/user")
    public User deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/event")
    public void postAddEvent(Event event) {

    }

    @Override
    @DeleteMapping("/event")
    public Event deleteEvent(int eventID) {
        throw new UnsupportedOperationException();
    }
}

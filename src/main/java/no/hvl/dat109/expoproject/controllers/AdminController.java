package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.UserEventService;
import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.*;
import no.hvl.dat109.expoproject.interfaces.controllers.IAdminController;
import no.hvl.dat109.expoproject.interfaces.database.*;
import no.hvl.dat109.expoproject.utils.PasswordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController implements IAdminController {

    private final IStandService ss;
    private final IUserService as;
    private final IEventService es;
    private final IVoteService vs;
    private final IUserEventService ues;

    public AdminController(IStandService ss, UserService as, EventService es, VoteService vs, UserEventService ues) {
        this.ss = ss;
        this.as = as;
        this.es = es;
        this.vs = vs;
        this.ues = ues;
    }

    @Override
    @GetMapping("/generate")
    public void generateVoteCodes(@RequestParam int nrOfCodes, @RequestParam int eventID) {
        List<String> voteCodes = vs.generateVoteCodes(nrOfCodes, eventID);
    }

    @GetMapping("/events")
    public List<Event> getEventsByUsername(@RequestParam String username) {
        return es.getEventsForUsername(username);
    }

    @GetMapping("/events/all")
    public List<Event> getAllEvents() {
        return es.findAll();
    }

    @GetMapping("/events/newest")
    public Event getNewestEvent() {
        return es.findNewest();
    }

    @Override
    @PostMapping("/user")
    public User postAddUser(@RequestBody User user) {
        if (as.userExists(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        String salt = PasswordUtils.generateSalt();
        String hash = PasswordUtils.hashWithSalt(user.getHash(), salt);
        user.setHash(hash);
        user.setSalt(salt);
        try {
            as.addUser(user);
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return user;
    }

    @GetMapping("/exhibitor/stand")
    public Stand findStandByExhibitor(@RequestParam String exhibitor){
        return ss.findStandByExhibitor(exhibitor);
    }

    @Override
    @PostMapping("/event/user")
    public boolean postAddUser(@RequestBody User user, @RequestHeader int eventID) {
        as.addUser(user);
        Event event = es.getEvent(eventID);
        UserEvent userEvent = new UserEvent(user, event);
        ues.addUserToEvent(userEvent);
        return true;
    }

    @Override
    @DeleteMapping("/user")
    public User deleteUser(@RequestHeader String username) {
        return as.removeUser(username);
    }

    @GetMapping("/event")
    public Event GetEventById(@RequestParam int eventID) {
        return es.getEvent(eventID);
    }

    @Override
    @PostMapping("/event")
    public Event postAddEvent(@RequestBody Event event) {

        Event addedEvent = null;

        try {
            addedEvent = es.addEvent(event);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return addedEvent;
    }

    @Override
    @DeleteMapping("/event")
    public void deleteEvent(@RequestParam int eventID) {
        es.removeEvent(eventID);
    }
}

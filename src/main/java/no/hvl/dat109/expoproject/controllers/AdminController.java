package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.UserEventService;
import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.controllers.IAdminController;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import no.hvl.dat109.expoproject.interfaces.database.IUserEventService;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController implements IAdminController {

    private final IUserService as;
    private final IEventService es;
    private final IVoteService vs;
    private final IUserEventService ues;

    public AdminController(UserService as, EventService es, VoteService vs,UserEventService ues) {
        this.as = as;
        this.es = es;
        this.vs = vs;
        this.ues = ues;
    }

    @Override
    @GetMapping("/generate")
    public void generateVoteCodes(@RequestParam int nrOfCodes,  @RequestParam int eventID) {
        List<String> voteCodes = vs.generateVoteCodes(nrOfCodes, eventID);
    }

    /*@PostMapping("/user")
    public void postAddUser() {

    }*/

    @Override
    @PostMapping("/user")
    public boolean postAddUser(@RequestBody User user, int eventID) {
        as.addUser(user);
        Event event = es.getEvent(eventID);
        UserEvent userEvent = new UserEvent(user, event);
        ues.addUserToEvent(userEvent);
        return true;
    }

    @Override
    @DeleteMapping("/user")
    public User deleteUser(String username) {
        return as.removeUser(username);
    }

    @Override
    @PostMapping("/event")
    public void postAddEvent(Event event) {
        es.addEvent(event);
    }

    @Override
    @DeleteMapping("/event")
    public void deleteEvent(int eventID) {
        es.removeEvent(eventID);
    }
}

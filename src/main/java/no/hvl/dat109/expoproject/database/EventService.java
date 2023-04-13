package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserEventRepo userEventRepo;

    @Autowired
    private UserService us;

    @Override
    public Event addEvent(Event event) {
        Event addedEvent = null;
        if(event == null){
            throw new NullPointerException("The event cannot be null");
        }
        else {
            if (eventRepo.findById(event.getId()) == null) {
                try {
                    eventRepo.save(event);
                    addedEvent = event;
                } catch (RuntimeException e) {
                    throw new RuntimeException("Event already exists");
                }
            }
        }
        return addedEvent;
    }

    @Override
    public void UpdateEvent(Event event) {
        if(event == null){
            throw new NullPointerException("The event cannot be null");
        }
        else
            eventRepo.save(event);
    }

    public Event getEvent(int id) {
        return eventRepo.findById(id);
    }

    @Override
    public Event removeEvent(int eventID) {
      return eventRepo.deleteById(eventID);
    }

    @Override
    public boolean isOpen(int eventID) {
        Event event = eventRepo.findById(eventID);
        return event.getEventStart().isBefore(LocalDateTime.now()) && event.getEventEnd().isAfter(LocalDateTime.now());
    }

    @Override
    public Event findEventById(int eventID) {
        return eventRepo.findById(eventID);
    }

    /*
     isOpenSetNow is for testing logic of isOpen()
     have to set NOW time for testing purposes
     */
    public boolean isOpenSetNow(int eventID, LocalDateTime time) {
        Event event = eventRepo.findById(eventID);
        return event.getEventStart().isBefore(time) && event.getEventEnd().isAfter(time);
    }

    @Override
    public List<User> getAllUsersInEvent(int eventID) {
        Event event = eventRepo.findById(eventID);
        List<UserEvent> userEvents = userEventRepo.findAllByEvent(event);
        return userEvents.stream().map(x -> x.getUser()).collect(Collectors.toList());
    }
    @Override
    public List<Event> getEventsForUsername(String username){
        List<Event> events = new ArrayList<>();
        User user = us.findUser(username);

        if(user == null){
            throw new NullPointerException("The user does not exits");
        }

        List<UserEvent> userEvents = userEventRepo.findAllByUser(user);
        for(UserEvent userEvent : userEvents){
            events.add(userEvent.getEvent());
        }

        return events;
    }
    @Override
    public List<Event> findAll(){
        return eventRepo.findAll();
    }

    @Override
    public Event findNewest(){
        return eventRepo.findAll().get(0);
    }
}

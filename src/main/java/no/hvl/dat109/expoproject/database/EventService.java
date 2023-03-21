package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserEventRepo userEventRepo;

    @Override
    public Event addEvent(Event event) throws Exception {
        Event addedEvent = null;
        if(event == null){
            throw new NullPointerException("The event cannot be null");
        }
        else if(eventRepo.findById(event.getId()) == null) {
                eventRepo.save(event);
                addedEvent = event;
        } else {
                throw new Exception("Event already exists");
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

    @Override
    public Event removeEvent(int eventID) {
        Event event = eventRepo.findById(eventID);

        if(event == null){
            //Means the event is not in the database
            throw new NullPointerException("The event was not found");
        }
        else {
            eventRepo.delete(event);
        }
        return event;
    }

    @Override
    public boolean isOpen(int eventID) {
        Event event = eventRepo.findById(eventID);
        return event.getEventStart().isBefore(LocalDateTime.now()) && event.getEventEnd().isAfter(LocalDateTime.now());
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
}

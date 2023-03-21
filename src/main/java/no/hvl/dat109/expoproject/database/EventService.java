package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepo eventRepo;
    @Override
    public void addEvent(Event event) {
        if(eventRepo.findById(event.getId())!=null){
            throw new RuntimeException("Event already exists");
        }
        eventRepo.save(event);
    }

    @Override
    public void UpdateEvent(Event event) {
        Event updateEvent = eventRepo.findById(event.getId());
        updateEvent.setId(event.getId());
        updateEvent.setName(event.getName());
        updateEvent.setEventStart(event.getEventStart());
        updateEvent.setEventEnd(event.getEventEnd());
        updateEvent.setUserEvent(event.getUserEvent());
        updateEvent.setVoters(event.getVoters());
        eventRepo.save(updateEvent);
    }
    @Override
    public Event removeEvent(int eventID) {
      return eventRepo.deleteById(eventID);
    }

    @Override
    public boolean isOpen(int eventID) {
        if(eventRepo.findById(eventID)!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsersInEvent(int eventID) {
        Event eventWithUsers = eventRepo.findById(eventID);
        List<UserEvent>userEvents = eventWithUsers.getUserEvent();
        List<User> users = userEvents.stream().map(u->u.getUser()).collect(Collectors.toList());

        return users;
    }

}
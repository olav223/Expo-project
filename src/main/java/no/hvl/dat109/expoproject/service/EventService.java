package no.hvl.dat109.expoproject.service;

import no.hvl.dat109.expoproject.database.EventRepo;
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
        eventRepo.save(event);
    }

    @Override
    public void UpdateEvent(Event event) {
        Event updateEvent = eventRepo.getEventById(event.getId());
        updateEvent.setId(event.getId());
        updateEvent.setName(event.getName());
        updateEvent.setEventStart(event.getEventStart());
        updateEvent.setEventEnd(event.getEventEnd());
        updateEvent.setUserEvent(event.getUserEvent());
        updateEvent.setVoters(event.getVoters());
        eventRepo.save(updateEvent);
    }
    @Override
    public void removeEvent(int eventID) {
        Event deleteEvent=eventRepo.getEventById(eventID);
        eventRepo.delete(deleteEvent);
    }

    @Override
    public boolean isOpen(int eventID) {
        if(eventRepo.getEventById(eventID)!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsersInEvent(int eventID) {
        Event eventWithUsers = eventRepo.getEventById(eventID);
        List<UserEvent>userEvents = eventWithUsers.getUserEvent();
        List<User> users = userEvents.stream().map(u->u.getUser()).collect(Collectors.toList());

        return users;
    }

}

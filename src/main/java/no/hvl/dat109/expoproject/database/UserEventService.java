package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IUserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventService implements IUserEventService {
    @Autowired
    UserEventRepo userEventRepo;

    @Override
    public void addUserToEvent(UserEvent userEvent) {
        User user = userEvent.getUser();
        Event event = userEvent.getEvent();

        if(user == null || event == null){
            throw new NullPointerException("Cannot add the user to event when the user or event is null");
        }

        List<UserEvent> userEvents = userEventRepo.findAllByEventId(event.getId());

        for(UserEvent users : userEvents){
            if(userEvent.equals(users)){
                throw new IllegalArgumentException("Duplicated users are not allowed");
            }
        }
        userEventRepo.save(userEvent);
    }

    @Override
    public void removeUserFromEvent(UserEvent userEvent) {
        List<UserEvent> userEvents = userEventRepo.findAllByEvent(userEvent.getEvent());

        if(userEvents.contains(userEvent)){
            userEventRepo.delete(userEvent);
        }else
            throw new NullPointerException("Cannot remove a user that does not exist");
    }
}

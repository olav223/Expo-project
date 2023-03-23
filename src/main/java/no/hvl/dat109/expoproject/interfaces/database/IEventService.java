package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;

import java.util.List;

public interface IEventService {
    Event getEvent(int id);

    /**
     * adds event to database from event object
     * @param event
     */
    void addEvent(Event event);

    /**
     * update event from an updated event object
     * @param event
     */
    void UpdateEvent(Event event);

    /**
     * removes an event with matching eventID from database
     * @param eventID
     */
    void removeEvent(int eventID);

    /**
     * checks if an event is open
     * @param eventID
     * @return true if event is open, false if closed
     */
    boolean isOpen(int eventID);
    List<User> getAllUsersInEvent(int eventID);
}

package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;

import java.util.List;

public interface IEventService {

    /**
     * adds event to database from event object
     * @param event
     * @return added event, null if the event already exists or event object is null
     */
    Event addEvent(Event event) throws Exception;

    /**
     * update event from an updated event object
     * @param event
     */
    void UpdateEvent(Event event);

    /**
     * removes an event with matching eventID from database
     * @param eventID
     * @return removed event
     */
    Event removeEvent(int eventID);

    /**
     * checks if an event is open
     * @param eventID
     * @return true if event is open, false if closed
     */
    boolean isOpen(int eventID);

    /**
     * @param id
     * @return
     */
    Event findEventById(int id);

    /**
     * @param eventID
     * @return
     */
    List<User> getAllUsersInEvent(int eventID);
}

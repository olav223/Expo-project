package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEventService {
    Event getEvent(int id);

    /**
     * adds event to database from event object
     *
     * @param event
     * @return added event, null if the event already exists or event object is null
     */
    Event addEvent(Event event) throws Exception;

    /**
     * update event from an updated event object
     *
     * @param event
     */
    void UpdateEvent(Event event);

    /**
     * removes an event with matching eventID from database
     *
     * @param eventID
     * @return removed event
     */
    Event removeEvent(int eventID);

    /**
     * checks if an event is open
     *
     * @param eventID
     * @return true if event is open, false if closed
     */
    boolean isOpen(int eventID);

    /**
     * finds and return an event with a given id
     *
     * @param id
     * @return event with the given id
     */
    Event findEventById(int id);

    /**
     * finds and returns all users in the event with the corresponding eventId
     *
     * @param eventID
     * @return a list of users in a given event
     */
    List<User> getAllUsersInEvent(int eventID);

    /**
     * Returns a list with all the events associated with the user
     *
     * @param username of the user we want to find
     * @return A list of events
     */
    List<Event> getEventsForUsername(String username);

    /**
     * finds and returns all events in the database
     *
     * @return list of all events
     */
    List<Event> findAll();

    /**
     * finds and returns the event, from all events with the earliest starting time
     *
     * @return Earliest event
     */
    Event findNewest();
}

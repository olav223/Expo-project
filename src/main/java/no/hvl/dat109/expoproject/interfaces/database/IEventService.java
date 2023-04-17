package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEventService {
    Event getEvent(int id);

    /**
     * Adds event to database from event object
     *
     * @param event The event to be added to the database
     * @return Added event, null if the event already exists or event object is null
     * @throws NullPointerException if event is null
     * @throws RuntimeException     if event already exists or database error
     */
    Event addEvent(Event event);

    /**
     * update event from an updated event object
     *
     * @param event
     */
    @Deprecated
    void UpdateEvent(Event event);

    /**
     * Removes an event with matching eventID from database
     *
     * @param eventID The id of the event to be removed
     * @return The removed event, null if event was not found
     */
    Event removeEvent(int eventID);

    /**
     * Checks if an event is open
     *
     * @param eventID The id of the event to be checked
     * @return true if event is open, false if closed
     */
    boolean isOpen(int eventID);

    /**
     * finds and return an event with a given id
     *
     * @param id The id of the event to be found
     * @return The event with the given id, or null if not found
     */
    Event findEventById(int id);

    /**
     * Finds and returns all users in the event with the corresponding eventId
     *
     * @param eventID The id of the event
     * @return A list of users in a given event, or an empty list if no users are found
     */
    List<User> getAllUsersInEvent(int eventID);

    /**
     * Returns a list with all the events associated with the user
     *
     * @param username The username of the user we want to find
     * @return A list of events, or an empty list if no events are found
     */
    List<Event> getEventsForUsername(String username);

    /**
     * Finds and returns all events in the database
     *
     * @return List of all events, or an empty list if no events are found
     */
    List<Event> findAll();

    /**
     * Finds and returns the event, from all events with the earliest starting time
     *
     * @return Newest event, or null if no events are found
     */
    Event findNewest();
}

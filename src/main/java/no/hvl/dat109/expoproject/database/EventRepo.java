package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo /*extends JpaRepository<Event,Integer>*/{

    /**
    @param id of the event
    @return the event with the id
     */
    Event getEventById(int id);

    /**
    @return a list of all events
     */
    List<Event>getAllEvents();

    /**
    @param id of event
    @return true if event is open, else
    @return false if event is closed
     */
    boolean isOpen(int id);
}

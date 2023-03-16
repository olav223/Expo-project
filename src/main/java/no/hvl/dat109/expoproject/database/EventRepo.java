package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface EventRepo extends JpaRepository<Event, Integer> {

    /**
     * @param id of the event
     * @return the event with the id
     */
    Event getById(int id);

    /**
     * @param id of event
     * @return true if event is open, else
     */
//    boolean isOpen(int id); // TODO finn query, bruk JPA designer?


}

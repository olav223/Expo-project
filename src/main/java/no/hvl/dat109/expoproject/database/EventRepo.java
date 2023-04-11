package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface EventRepo extends JpaRepository<Event, Integer> {

    /**
     * @param id ID til eventen
     * @return Eventen til id, eller null
     */
    Event findById(int id);

    /**
     * deletes the event from the database with the given id (eventId)
     *
     * @param id
     * @return deleted event
     */
    Event deleteById(int id);

    /**
     * returns from the database, all events
     *
     * @return a list of all events
     */
    @Query("select u from Event u order by u.eventStart desc")
    List<Event> findAll();
}

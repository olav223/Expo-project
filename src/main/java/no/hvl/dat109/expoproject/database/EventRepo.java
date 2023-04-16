package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for å håndtere eventer i databasen.
 */
@Repository
interface EventRepo extends JpaRepository<Event, Integer> {

    /**
     * Henter ut en Event fra databasen med gitt primærnøkkel.
     *
     * @param id Id til eventen
     * @return Eventen til id, eller null hvis ingen ble funnet.
     */
    Event findById(int id);

    /**
     * Deletes the event from the database with the given id (eventId)
     *
     * @param id Id til eventen
     * @return Eventen som ble slettet, eller null hvis ingen ble funnet.
     */
    Event deleteById(int id);

    /**
     * Henter alle eventer fra databasen, og sorterer dem etter starttid synkende.
     *
     * @return Alle eventer
     */
    @Query("select u from Event u order by u.eventStart desc")
    List<Event> findAll();
}

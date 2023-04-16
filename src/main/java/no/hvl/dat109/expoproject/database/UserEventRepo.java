package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for å håndtere bruker-event i databasen.
 */
@Repository
interface UserEventRepo extends JpaRepository<UserEvent, UserEventPK> {

    /**
     * Henter alle UserEvents for en gitt event.
     *
     * @param event Eventen vi skal hente brukere for
     * @return En liste over alle brukere som er påmeldt en event, eller en tom liste hvis ingen ble funnet.
     */
    List<UserEvent> findAllByEvent(Event event);

    /**
     * Finds and returns a list of all the events a user is registered to attend
     *
     * @param user The user to find events for
     * @return A list af all events for a given user
     */
    List<UserEvent> findAllByUser(User user);

}

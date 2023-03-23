package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface UserEventRepo extends JpaRepository<UserEvent, UserEventPK> {

    /**
     * @param event Eventen vi skal hente brukere for
     * @return En liste over alle brukere som er påmeldt en event
     */
    List<UserEvent> findAllByEvent(Event event);

    List<UserEvent> findAllByEvent(int id);


}
